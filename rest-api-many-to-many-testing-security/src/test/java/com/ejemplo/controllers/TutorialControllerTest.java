package com.ejemplo.controllers;

import java.util.List;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.ejemplo.DatosPrueba;
import com.ejemplo.controller.TutorialController;
import com.ejemplo.entity.Tutorial;
import com.ejemplo.repository.TutorialRepository;

import tools.jackson.databind.ObjectMapper;

// Test integración a la capa de controladores que conlleva peticiones HTTP
// @WebMvcTest no funciona con Spring Security porque no carga en contexto de Spring donde se empleará @SpringBootTest
@WebMvcTest(controllers = TutorialController.class)
// Dejar la base de datos como estaba
@AutoConfigureTestDatabase(replace = Replace.NONE)
// Realizar peticiones a los end points
@AutoConfigureMockMvc(addFilters = false)
public class TutorialControllerTest {

  // Crear bean para incluirlo con dependencias
  @Autowired
  MockMvc mockMvc;
  @Autowired
  ObjectMapper objectMapper;

  // Simulado
  @MockitoBean
  TutorialRepository tutorialRepository;

  // Datos de prueba
  List<Tutorial> listaTutorials;

  @BeforeEach
  @SuppressWarnings("unused")
  void setUp() {
    // Carga de datos    
    this.listaTutorials = new DatosPrueba().getListaTutorials();
  }

  @Test
  @DisplayName("Test de controlador sin seguridad para recuperar todos los tutoriales")
  void testFindAll() throws Exception {
    // given
    given(tutorialRepository.findAll()).willReturn(this.listaTutorials);
    // when
    ResultActions resultActions = mockMvc.perform(
      get("/api/tutorials").accept(MediaType.APPLICATION_JSON)
    );
    // then
    resultActions.andExpect(status().isOk())
      .andDo(print())
      .andExpect(jsonPath("$.size()", is(this.listaTutorials.size())));
  }

  @Test
  @DisplayName("Test de controlador sin seguridad para persistir un tutorial")
  void testSaveTutorial() throws Exception {
    // given
    Tutorial tutorial1 = this.listaTutorials.get(0);
    given(tutorialRepository.save(any(Tutorial.class)))
      .willAnswer(invocation -> invocation.getArgument(0));
    // when
    ResultActions resultActions = this.mockMvc.perform(
      post("/api/tutorials")
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(tutorial1))
    );
    // then
    resultActions.andDo(print())
      .andExpect(status().isCreated())
      .andExpect(jsonPath("$.title", is(tutorial1.getTitulo())))
      .andExpect(jsonPath("$.description", is(tutorial1.getDescripcion())));
  }

  @Test
  @DisplayName("Test de controlador sin seguridad para recuperar un tutorial por su id")
  void testRecuperarTutorialPorId() throws Exception {
    long tutorialId = 1;
    Tutorial tutorial1 = this.listaTutorials.get(0);
    // given
    given(tutorialRepository.findById(tutorialId)).willReturn(Optional.of(tutorial1));
    // when
    ResultActions resultActions = mockMvc.perform(get("/api/tutorials/{id}", tutorialId));
    // then
    resultActions.andDo(print())
      .andExpect(status().isOk())
      .andExpect(jsonPath("$.title", is(tutorial1.getTitulo())));
  }

  @Test
  @DisplayName("Test de controlador sin seguridad para un tutorial no encontrado")
  void testTutorialNoEncontrado() throws Exception {
    long tutorialId = 1;
    // given
    given(tutorialRepository.findById(tutorialId)).willReturn(Optional.empty());
    // when
    ResultActions resultActions = mockMvc.perform(get("/api/tutorials/{id}", tutorialId));
    // then
    resultActions.andDo(print()).andExpect(status().isNotFound());
  }

  @Test
  @DisplayName("Test de controlador sin seguridad para actualizar un tutorial")
  void testActualizarTutorial() throws Exception {
    long tutorialId = 1;
    Tutorial tutorial1 = this.listaTutorials.get(0);
    // given
    given(tutorialRepository.findById(tutorialId)).willReturn(Optional.of(tutorial1));
    given(tutorialRepository.save(any(Tutorial.class)))
      .willAnswer(invocation -> invocation.getArgument(0));
    // when
    ResultActions resultActions = mockMvc.perform(
      put("/api/tutorials/{id}", tutorialId)
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(tutorial1))
    );
    // then
    resultActions.andDo(print())
      .andExpect(status().isOk())
      .andExpect(jsonPath("$.title", is(tutorial1.getTitulo())))
      .andExpect(jsonPath("$.description", is(tutorial1.getDescripcion())));
  }

  @Test
  @DisplayName("Test de controlador sin seguridad para eliminar un tutorial")
  void testDeleteTutorial() throws Exception {
    long tutorialId = 1;
    Tutorial tutorial1 = this.listaTutorials.get(0);
    // given
    given(tutorialRepository.findById(tutorialId)).willReturn(Optional.of(tutorial1));
    willDoNothing().given(tutorialRepository).delete(tutorial1);
    // when
    ResultActions resultActions = mockMvc.perform(
      delete("/api/tutorials/{id}", tutorialId).accept(MediaType.APPLICATION_JSON)
    );
    // then
    resultActions.andExpect(status().isNoContent()).andDo(print());
  }

  @Test
  @DisplayName("Test de controlador sin seguridad para recuperar todos los tutoriales publicados")
  void testFindPublished() throws Exception {
    // given
    List<Tutorial> listaTutorialsPublished = this.listaTutorials.stream().filter(t -> t.isPublicado()).toList();
    given(tutorialRepository.findByPublicado(true)).willReturn(listaTutorialsPublished);
    // when
    ResultActions resultActions = mockMvc.perform(
      get("/api/tutorials/published").accept(MediaType.APPLICATION_JSON)
    );
    // then
    resultActions.andExpect(status().isOk())
      .andDo(print())
      .andExpect(jsonPath("$.size()", is(listaTutorialsPublished.size()))
    );
  }

}
