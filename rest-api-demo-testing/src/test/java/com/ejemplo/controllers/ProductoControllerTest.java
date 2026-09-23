package com.ejemplo.controllers;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.ejemplo.DatosPrueba;
import com.ejemplo.entities.Producto;
import com.ejemplo.services.ProductoService;
import com.ejemplo.utilities.FileDownloadUtil;
import com.ejemplo.utilities.FileUploadUtil;

import tools.jackson.databind.ObjectMapper;

// Test integración a la capa de controladores que conlleva peticiones HTTP
// @WebMvcTest no funciona con Spring Security porque no carga en contexto de Spring donde se empleará @SpringBootTest
@WebMvcTest(controllers = ProductoController.class)
// Dejar la base de datos como estaba
@AutoConfigureTestDatabase(replace = Replace.NONE)
// Realizar peticiones a los end points
@AutoConfigureMockMvc
public class ProductoControllerTest {

  // Crear bean para incluirlo con dependencias
  @Autowired
  MockMvc mockMvc;
  @Autowired
  ObjectMapper objectMapper;

  // Simulado
  @MockitoBean
  ProductoService productoService;
  @MockitoBean
  FileUploadUtil fileUploadUtil;
  @MockitoBean
  FileDownloadUtil fileDownloadUtil;

  // Datos de prueba
  List<Producto> listaProductos;

  @BeforeEach
  @SuppressWarnings("unused")
  void setUp() {
    this.listaProductos = new DatosPrueba().getListaProductos();
  }

  @Test
  @DisplayName("Test de controlador para recuperar todos los productos")
  void testFindAll() throws Exception {
    // given
    given(productoService.findAll(Sort.by("nombre"))).willReturn(listaProductos);
    // when
    ResultActions resultActions = mockMvc.perform(
      get("/productos").accept(MediaType.APPLICATION_JSON));
    // then
    resultActions.andExpect(status().isOk())
      .andDo(print())
      .andExpect(jsonPath("$.productos.size()", is(listaProductos.size())));
  }

  @Test
  @DisplayName("Test de controlador para persistir un producto")
  void testSaveProducto() throws Exception {
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
    ResultActions resultActions;
      resultActions = this.mockMvc.perform(
        multipart("/productos").file("file", null).file(bytesArrayProducto)
      );
    // then
    resultActions.andDo(print())
      .andExpect(status().isCreated())
      .andExpect(jsonPath("$.producto_persistido.nombre", is(producto1.getNombre())))
      .andExpect(jsonPath("$.producto_persistido.descripcion", is(producto1.getDescripcion())));
  }

  @Test
  @DisplayName("Test de controlador para recuperar un producto por su id")
  void testRecuperarProductoPorId() throws Exception {
    int productoId = 1;
    Producto producto1 = listaProductos.get(0);
    // given
    given(productoService.findById(productoId)).willReturn(producto1);
    // when
    ResultActions resultActions = mockMvc.perform(get("/productos/{id}", productoId));
    // then
    resultActions.andDo(print())
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.producto_encontrado.nombre()", is(producto1.getNombre())));
  }

  @Test
  @DisplayName("Test de controlador para un producto no encontrado")
  void testProductoNoEncontrado() throws Exception {
    int productoId = 1;
    // given
    given(productoService.findById(productoId)).willReturn(null);
    // when
    ResultActions resultActions = mockMvc.perform(get("/productos/{id}", productoId));
    // then
    resultActions.andDo(print()).andExpect(status().isNotFound());
  }
}
