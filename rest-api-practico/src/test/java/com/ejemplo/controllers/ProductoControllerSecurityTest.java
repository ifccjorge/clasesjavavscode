package com.ejemplo.controllers;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.ejemplo.DatosPrueba;
import com.ejemplo.entities.Producto;
import com.ejemplo.services.ProductoService;
import com.ejemplo.spring_security_jwt.payload.request.LoginRequest;
import com.ejemplo.utilities.FileDownloadUtil;
import com.ejemplo.utilities.FileUploadUtil;
import com.ejemplo.utilities.FileUtil;

import tools.jackson.databind.ObjectMapper;

// Test integración a la capa de controladores que conlleva peticiones HTTP
// @WebMvcTest no funciona con Spring Security porque no carga en contexto de Spring donde se empleará @SpringBootTest
@SpringBootTest
// Dejar la base de datos como estaba
@AutoConfigureTestDatabase(replace = Replace.NONE)
// Realizar peticiones a los end points
@AutoConfigureMockMvc
public class ProductoControllerSecurityTest {

  // Crear bean para incluirlo con dependencias
  @Autowired
  MockMvc mockMvc;
  @Autowired
  ObjectMapper objectMapper;

  // Simulado
  @MockitoBean
  ProductoService productoService;
  @MockitoBean
  @SuppressWarnings("unused")
  FileDownloadUtil fileDownloadUtil;
  @MockitoBean
  @SuppressWarnings("unused")
  FileUploadUtil fileUploadUtil;
  @MockitoBean
  @SuppressWarnings("unused")
  FileUtil fileUtil;

  // Token
  String token;

  // Datos de prueba
  List<Producto> listaProductos;

  @BeforeEach
  @SuppressWarnings("unused")
  void setUp() throws Exception {
    // Token válido
    LoginRequest loginRequest = LoginRequest.builder()
      .username("tecnico")
      .password("Temp2026$")
      .build();
    String jsonLogginRequest = objectMapper.writeValueAsString(loginRequest);
    ResultActions resultActions = this.mockMvc.perform(
      post("/api/auth/signin")
        .contentType(MediaType.APPLICATION_JSON)
        .content(jsonLogginRequest)
    );
    // Extraer el token
    MvcResult mvcResult = resultActions.andDo(print()).andReturn();
    String contentAsString = mvcResult.getResponse().getContentAsString();
    JSONObject json = new JSONObject(contentAsString);
    this.token = "Bearer " + json.getString("token");
    // Carga de datos
    this.listaProductos = new DatosPrueba().getListaProductos();

  }

  @Test
  @DisplayName("Test de controlador para recuperar todos los productos")
  void testFindAllSecurity() throws Exception {
    // given
    given(productoService.findAll(Sort.by("nombre"))).willReturn(listaProductos);
    // when
    ResultActions resultActions = mockMvc.perform(
      get("/productos")
        .accept(MediaType.APPLICATION_JSON)
        .header("Authorization", this.token)
    );
    // then
    resultActions.andExpect(status().isOk())
      .andDo(print())
      .andExpect(jsonPath("$.productos.size()", is(listaProductos.size())));
  }
  @Test
  @DisplayName("Test de controlador para persistir un producto")
  void testSaveProductoSecurity() throws Exception {
    // given
    Producto producto1 = listaProductos.get(0);
    given(productoService.save(any(Producto.class)))
      .willAnswer(invocation -> invocation.getArgument(0));
    // when
    String jsonStringProducto = objectMapper.writeValueAsString(producto1);
    MockMultipartFile bytesArrayProducto = new MockMultipartFile(
      "producto",
      null,
      "application/json",
      jsonStringProducto.getBytes()
    );
    ResultActions resultActions = this.mockMvc.perform(
      multipart("/productos")
        .file("file", null)
        .file(bytesArrayProducto)
        .header("Authorization", this.token)
    );
    // then
    resultActions.andDo(print())
      .andExpect(status().isCreated())
      .andExpect(jsonPath("$.producto_persistido.nombre", is(producto1.getNombre())))
      .andExpect(jsonPath("$.producto_persistido.descripcion", is(producto1.getDescripcion())));
  }

  @Test
  @DisplayName("Test de controlador para recuperar un producto por su id")
  void testRecuperarProductoPorIdSecurity() throws Exception {
    int productoId = 1;
    Producto producto1 = listaProductos.get(0);
    // given
    given(productoService.findById(productoId)).willReturn(producto1);
    // when
    ResultActions resultActions = mockMvc.perform(
      get("/productos/{id}", productoId).header("Authorization", this.token)
    );
    // then
    resultActions.andDo(print())
      .andExpect(status().isOk())
      .andExpect(jsonPath("$.producto_encontrado.nombre", is(producto1.getNombre())));
  }

  @Test
  @DisplayName("Test de controlador para un producto no encontrado")
  void testProductoNoEncontradoSecurity() throws Exception {
    int productoId = 1;
    // given
    given(productoService.findById(productoId)).willReturn(null);
    // when
    ResultActions resultActions = mockMvc.perform(
      get("/productos/{id}", productoId).header("Authorization", this.token)
    );
    // then
    resultActions.andDo(print()).andExpect(status().isNotFound());
  }

  @Test
  @DisplayName("Test de controlador para actualizar un producto sin imagen")
  void testActualizarProductoSinImagenSecurity() throws Exception {
    int productoId = 1;
    Producto producto1 = listaProductos.get(0);
    // given
    given(productoService.findById(productoId)).willReturn(producto1);
    given(productoService.save(any(Producto.class)))
      .willAnswer(invocation -> invocation.getArgument(0));
    // when
    ResultActions resultActions = mockMvc.perform(
      put("/productos/sinimagen/{id}", productoId)
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(producto1))
        .header("Authorization", this.token)
    );
    // then
    resultActions.andDo(print())
      .andExpect(status().isOk())
      .andExpect(jsonPath("$.producto_actualizado.nombre", is(producto1.getNombre())))
      .andExpect(jsonPath("$.producto_actualizado.descripcion", is(producto1.getDescripcion())))
      .andExpect(jsonPath("$.producto_actualizado.imagen", is(producto1.getProductoImage())));
  }

  @Test
  @DisplayName("Test de controlador para persistir un producto con imagen")
  void testActualizarProductoConImagenSecurity() throws Exception {
    int productoId = 1;
    Producto producto1 = listaProductos.get(0);
    // given
    given(productoService.findById(productoId)).willReturn(producto1);
    given(productoService.save(any(Producto.class)))
      .willAnswer(invocation -> invocation.getArgument(0));
    // when
    String jsonStringProducto = objectMapper.writeValueAsString(producto1);
    MockMultipartFile bytesArrayProducto = new MockMultipartFile(
      "producto",
      null,
      "application/json",
      jsonStringProducto.getBytes());
    ResultActions resultActions = this.mockMvc.perform(
      multipart("/productos/{id}", productoId)
        .with(request -> {
          request.setMethod("PUT");
          return request;
        }
      )
      .file("file", null)
      .file(bytesArrayProducto)
      .header("Authorization", this.token)
    );
    // then
    resultActions.andDo(print())
      .andExpect(status().isCreated())
      .andExpect(jsonPath("$.producto_actualizado.nombre", is(producto1.getNombre())))
      .andExpect(jsonPath("$.producto_actualizado.descripcion", is(producto1.getDescripcion())))
      .andExpect(jsonPath("$.producto_actualizado.imagen", is(producto1.getProductoImage())));
  }

  @Test
  @DisplayName("Test de controlador para eliminar un producto")
  void testDeleteProductoSecurity() throws Exception {
    int productoId = 1;
    Producto producto1 = listaProductos.get(0);
    // given
    given(productoService.findById(productoId)).willReturn(producto1);
    willDoNothing().given(productoService).delete(producto1);
    // when
    ResultActions resultActions = mockMvc.perform(
      delete("/productos/{id}", productoId)
        .accept(MediaType.APPLICATION_JSON)
        .header("Authorization", this.token)
    );
    // then
    resultActions.andExpect(status().isOk()).andDo(print());
  }

}
