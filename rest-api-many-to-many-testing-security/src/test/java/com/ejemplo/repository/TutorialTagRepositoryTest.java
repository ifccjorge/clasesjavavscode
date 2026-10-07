package com.ejemplo.repository;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase.Replace;

import com.ejemplo.DatosPrueba;
import com.ejemplo.entity.Tag;
import com.ejemplo.entity.Tutorial;

// No levanta todo el contexto, es transacional
@DataJpaTest
// Restaura la base de datos tras la prueba
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class TutorialTagRepositoryTest {

  @Autowired
  private TutorialRepository tutorialRepository;
  @Autowired
  private TagRepository tagRepository;

  private Tutorial tutorial1, tutorial2;
  private Tag tag1, tag2;

  @BeforeEach
  @SuppressWarnings("unused")
  void setUp() {
    DatosPrueba datosPrueba = new DatosPrueba();
    this.tutorial1 = datosPrueba.getListaTutorials().get(0);
    this.tutorial2 = datosPrueba.getListaTutorials().get(1);
    this.tag1 = datosPrueba.getListaTags().get(0);
    this.tag2 = datosPrueba.getListaTags().get(1);
  }

  @Test
  @DisplayName("Test para persistir tutorial y tag")
  void testTutorialTagRepositoryTest() {
    // given
    Tag tagGuardado1 = tagRepository.save(tag1);
    Tag tagGuardado2 = tagRepository.save(tag2);
    tutorial1.addTag(tag1);
    tutorial1.addTag(tag2);
    tutorial2.addTag(tag2);
    // when
    Tutorial tutorialGuardado1 = tutorialRepository.save(tutorial1);
    Tutorial tutorialGuardado2 = tutorialRepository.save(tutorial2);
    // then
    assertThat(tutorialGuardado1).isNotNull();
    assertThat(tutorialGuardado1.getId()).isEqualTo(1);
    assertThat(tutorialGuardado1.getTags().size()).isEqualTo(2);
    assertThat(tutorialGuardado2).isNotNull();
    assertThat(tutorialGuardado2.getId()).isEqualTo(2);
    assertThat(tutorialGuardado2.getTags().size()).isEqualTo(1);
    assertThat(tagGuardado1).isNotNull();
    assertThat(tagGuardado1.getId()).isEqualTo(1);
    assertThat(tagGuardado1.getTutorials().size()).isEqualTo(1);
    assertThat(tagGuardado2).isNotNull();
    assertThat(tagGuardado2.getId()).isEqualTo(2);
    assertThat(tagGuardado2.getTutorials().size()).isEqualTo(2);
  }

}
