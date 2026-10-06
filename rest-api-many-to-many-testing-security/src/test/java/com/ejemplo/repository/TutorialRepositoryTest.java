package com.ejemplo.repository;

import java.math.BigDecimal;

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
public class TutorialRepositoryTest {

  @Autowired
  private TutorialRepository tutorialRepository;
  @Autowired
  private TagRepository tagRepository;

  private Tutorial tutorial1;
  private Tutorial tutorial2;
  private Tag tag1;
  private Tag tag2;

  @BeforeEach
  @SuppressWarnings("unused")
  void setUp() {
    DatosPrueba datosPrueba = new DatosPrueba();
  }

  @Test
  @DisplayName("Test para persistir")
  void testProductoDaoTest() {
    // given
    Presentacion presentacion0 = presentacionDao.save(presentacionPorUndades);
    Presentacion presentacion1 = presentacionDao.save(presentacionPorDecenas);
    producto0 = Producto.builder()
      .nombre("Google Pixel 11 Pro")
      .descripcion("Google Smart Phone")
      .precio(new BigDecimal(900))
      .presentacion(presentacion0)
      .build();
    producto1 = Producto.builder()
      .nombre("Tornillos fijadores")
      .descripcion("Tornillos fijadores de pared")
      .precio(new BigDecimal(2.5))
      .presentacion(presentacion1)
      .build();
    // when
    Tutorial tutorialGuardado1 = tutorialRepository.save(tutorial1);
    Tutorial tutorialGuardado2 = tutorialRepository.save(tutorial2);
    // then
    assertThat(tutorialGuardado1).isNotNull();
    assertThat(tutorialGuardado1.getId()).isEqualTo(1);
    assertThat(tutorialGuardado2).isNotNull();
    assertThat(tutorialGuardado2.getId()).isEqualTo(2);
  }

}
