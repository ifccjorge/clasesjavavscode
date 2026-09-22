package com.ejemplo.controllers;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.mockito.BDDMockito.given;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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
@WebMvcTest(MockitoExtension.class)
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
  private ProductoService productoService;
  @MockitoBean
  private FileUploadUtil fileUploadUtil;
  @MockitoBean
  private FileDownloadUtil fileDownloadUtil;

  @Test
  @DisplayName("Test de controlador para recuperar todos los productos")
  void testFindAll() {
    // given
    List<Producto> listaProductos = new DatosPrueba().getListaProductos();
    given(productoService.findAll(Sort.by("nombre"))).willReturn(listaProductos);
    // when
    try {
      ResultActions resultActions = mockMvc.perform(get("/productos/listado")
        .accept(MediaType.APPLICATION_JSON));
      // then
      resultActions.andExpect(status().isOk())
      .andDo(print())
      .andExpect(jsonPath("$.productos.size()", is(listaProductos.size())));
    } catch (Exception ex) {
        System.getLogger(ProductoControllerTest.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
    }
  }

}
