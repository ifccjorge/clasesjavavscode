package com.ejemplo.services;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.BDDMockito.given;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.ejemplo.dao.ProductoDao;
import com.ejemplo.entities.Presentacion;
import com.ejemplo.entities.Producto;

@ExtendWith(MockitoExtension.class)
public class ProductoServiceImpTest {

  @Mock
  private ProductoDao productoDao;
  @InjectMocks
  private ProductoServiceImp productoServiceImp;

  private Presentacion presentacionPorUndades, presentacionPorDecenas;
  private Producto producto1, producto2;
  List<Producto> listaProductos = new ArrayList<>();

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
    producto1 = Producto.builder()
      .nombre("Google Pixel 11 Pro")
      .descripcion("Google Smart Phone")
      .precio(new BigDecimal(900))
      .existencias(5)
      .presentacion(presentacionPorUndades)
      .build();
    producto2 = Producto.builder()
      .nombre("Tornillos fijadores")
      .descripcion("Tornillos fijadores de pared")
      .precio(new BigDecimal(2.5))
      .existencias(50)
      .presentacion(presentacionPorDecenas)
      .build();
    listaProductos.add(producto1);
    listaProductos.add(producto2);
  }

  @Test
  @DisplayName("Test de servicio para persistir un producto")
  void testSave() {
    // given
    given(productoDao.save(producto1)).willReturn(producto1);
    // when
    Producto productoGuardado = productoServiceImp.save(producto1);
    // then
    assertThat(productoGuardado).isNotNull();
    assertThat(productoGuardado.getExistencias()).isEqualTo(5);
  }

}
