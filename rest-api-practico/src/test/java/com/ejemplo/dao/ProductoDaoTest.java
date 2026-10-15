package com.ejemplo.dao;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase.Replace;

import com.ejemplo.entities.Presentacion;
import com.ejemplo.entities.Producto;

// No levanta todo el contexto, es transacional
@DataJpaTest
// Restaura la base de datos tras la prueba
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class ProductoDaoTest {

  @Autowired
  private PresentacionDao presentacionDao;
  @Autowired
  private ProductoDao productoDao;

  private Presentacion presentacionPorUndades;
  private Presentacion presentacionPorDecenas;
  private Producto producto0;
  private Producto producto1;

  @BeforeEach
  @SuppressWarnings("unused")
  void setUp() {
    presentacionPorUndades = Presentacion.builder()
      .nombre("Unidad")
      .descripcion("Por unidades")
      .build();
    presentacionPorDecenas = Presentacion.builder()
      .nombre("Decenas")
      .descripcion("Por decenas")
      .build();
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
    Producto productoGuardado0 = productoDao.save(producto0);
    Producto productoGuardado1 = productoDao.save(producto1);
    // then
    assertThat(productoGuardado0).isNotNull();
    assertThat(productoGuardado0.getId()).isEqualTo(1);
    assertThat(productoGuardado1).isNotNull();
    assertThat(productoGuardado1.getId()).isEqualTo(2);
  }

}
