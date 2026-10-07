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
import com.ejemplo.entity.Tutorial;
import com.ejemplo.repository.TutorialRepository;
import com.ejemplo.spring_security_jwt.payload.request.LogginRequest;
import tools.jackson.databind.ObjectMapper;

// Test integración a la capa de controladores que conlleva peticiones HTTP
// @WebMvcTest no funciona con Spring Security porque no carga en contexto de Spring donde se empleará @SpringBootTest
@SpringBootTest
// Dejar la base de datos como estaba
@AutoConfigureTestDatabase(replace = Replace.NONE)
// Realizar peticiones a los end points
@AutoConfigureMockMvc
public class TutorialControllerSecurityTest {

  // Crear bean para incluirlo con dependencias
  @Autowired
  MockMvc mockMvc;
  @Autowired
  ObjectMapper objectMapper;

  // Simulado
  @MockitoBean
  TutorialRepository tutorialRepository;

  // Token
  String token;

  // Datos de prueba
  List<Tutorial> listaTutorials;

  @BeforeEach
  @SuppressWarnings("unused")
  void setUp() throws Exception {
    // Token válido
    LogginRequest logginRequest = LogginRequest.builder()
      .email("tecnico@server.net")
      .password("Temp2026$")
      .build();
    String jsonLogginRequest = objectMapper.writeValueAsString(logginRequest);
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
    this.listaTutorials = new DatosPrueba().getListaTutorials();
  }

  @Test
  @DisplayName("Test de controlador para recuperar todos los tutoriales")
  void testFindAllSecurity() throws Exception {
    // given
    given(tutorialRepository.findAll(Sort.by("nombre"))).willReturn(listaTutorials);
    // when
    ResultActions resultActions = mockMvc.perform(
      get("/tutorials")
        .accept(MediaType.APPLICATION_JSON)
        .header("Authorization", this.token)
    );
    // then
    resultActions.andExpect(status().isOk())
      .andDo(print())
      .andExpect(jsonPath("$.tutorials.size()", is(this.listaTutorials.size())));
  }
  
  @Test
  @DisplayName("Test de controlador para persistir un tutorial")
  void testSaveTutorialSecurity() throws Exception {
    // given
    Tutorial tutorial1 = this.listaTutorials.get(0);
    given(tutorialRepository.save(any(Tutorial.class)))
        .willAnswer(invocation -> invocation.getArgument(0));
    // when
    String jsonStringTutorial = objectMapper.writeValueAsString(tutorial1);
    MockMultipartFile bytesArrayProducto = new MockMultipartFile(
      "producto",
      null,
      "application/json",
      jsonStringTutorial.getBytes()
    );
    ResultActions resultActions = this.mockMvc.perform(
      multipart("/tutorials")
        .file("file", null)
        .file(bytesArrayProducto)
        .header("Authorization", this.token)
    );
    // then
    resultActions.andDo(print())
      .andExpect(status().isCreated())
      .andExpect(jsonPath("$.producto_persistido.titulo", is(tutorial1.getTitulo)))
      .andExpect(jsonPath("$.producto_persistido.descripcion", is(tutorial1.getDescripcion())));
  }

  @Test
  @DisplayName("Test de controlador para recuperar un tutorial por su id")
  void testRecuperarTutorialPorIdSecurity() throws Exception {
    long tutorialId = 1;
    Tutorial tutorial1 = listaTutorials.get(0);
    // given
    given(tutorialRepository.findById(tutorialId).orElse(null)).willReturn(tutorial1);
    // when
    ResultActions resultActions = mockMvc.perform(
      get("/tutorials/{id}", tutorialId).header("Authorization", this.token)
    );
    // then
    resultActions.andDo(print())
      .andExpect(status().isOk())
      .andExpect(jsonPath("$.producto_encontrado.titulo", is(tutorial1.getTitulo())));
  }

  @Test
  @DisplayName("Test de controlador para un tutorial no encontrado")
  void testTutorialNoEncontradoSecurity() throws Exception {
    long tutorialId = 1;
    // given
    given(tutorialRepository.findById(tutorialId)).willReturn(null);
    // when
    ResultActions resultActions = mockMvc
        .perform(get("/tutorials/{id}", tutorialId).header("Authorization", this.token)
    );
    // then
    resultActions.andDo(print()).andExpect(status().isNotFound());
  }

  @Test
  @DisplayName("Test de controlador para actualizar un tutorial")
  void testActualizarTutorialSecurity() throws Exception {
    long tutorialId = 1;
    Tutorial tutorial1 = listaTutorials.get(0);
    // given
    given(tutorialRepository.findById(tutorialId).orElse(null)).willReturn(tutorial1);
    given(tutorialRepository.save(any(Tutorial.class)))
      .willAnswer(invocation -> invocation.getArgument(0));
    // when
    ResultActions resultActions = mockMvc.perform(
      put("/tutorials/{id}", tutorialId)
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(tutorial1))
        .header("Authorization", this.token)
    );
    // then
    resultActions.andDo(print())
      .andExpect(status().isOk())
      .andExpect(jsonPath("$.producto_actualizado.titulo", is(tutorial1.getTitulo())))
      .andExpect(jsonPath("$.producto_actualizado.descripcion", is(tutorial1.getDescripcion())));
  }

  @Test
  @DisplayName("Test de controlador para eliminar un tutorial")
  void testDeleteProductoSecurity() throws Exception {
    long productoId = 1;
    Tutorial tutorial1 = listaTutorials.get(0);
    // given
    given(tutorialRepository.findById(productoId).orElse(null)).willReturn(tutorial1);
    willDoNothing().given(tutorialRepository).delete(tutorial1);
    // when
    ResultActions resultActions = mockMvc.perform(
      delete("/tutorials/{id}", productoId)
        .accept(MediaType.APPLICATION_JSON)
        .header("Authorization", this.token)
    );
    // then
    resultActions.andExpect(status().isOk()).andDo(print());
  }

}
