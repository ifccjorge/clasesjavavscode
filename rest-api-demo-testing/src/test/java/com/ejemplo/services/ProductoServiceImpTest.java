package com.ejemplo.services;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.BDDMockito.given;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;

import com.ejemplo.DatosPrueba;
import com.ejemplo.dao.ProductoDao;
import com.ejemplo.entities.Producto;

@ExtendWith(MockitoExtension.class)
public class ProductoServiceImpTest {

  @Mock
  private ProductoDao productoDao;
  @InjectMocks
  private ProductoServiceImp productoServiceImp;

  private Producto producto1, producto2;
  List<Producto> listaProductos;

  @BeforeEach
  @SuppressWarnings("unused")
  void setUp() {
    listaProductos = new DatosPrueba().getListaProductos();
    producto1 = listaProductos.get(0);
    producto2 = listaProductos.get(1);
  }

  @Test
  @DisplayName("Test de servicio para persistir un producto")
  void testSave() {
    // given
    given(productoDao.save(producto1)).willReturn(producto1);
    given(productoDao.save(producto2)).willReturn(producto2);
    // when
    Producto productoGuardado1 = productoServiceImp.save(producto1);
    Producto productoGuardado2 = productoServiceImp.save(producto2);
    // then
    assertThat(productoGuardado1).isNotNull();
    assertThat(productoGuardado1.getExistencias()).isEqualTo(5);
    assertThat(productoGuardado2).isNotNull();
    assertThat(productoGuardado2.getExistencias()).isEqualTo(50);
  }

  @Test
  @DisplayName("Test de servicio para recuperar una lista vacía de productos")
  void testEmptyProductList() {
    // given
    given(productoDao.findAll()).willReturn(Collections.emptyList());
    // when
    List<Producto> productos = productoServiceImp.findAll();
    // then
    assertThat(productos).isEmpty();
  }

  @Test
  @DisplayName("Test de servicio para recuperar los dos productos creados")
  void testFindAll() {
    // when
    when(productoServiceImp.findAll()).thenReturn(listaProductos);
    // then
    assertEquals(2, productoServiceImp.findAll().size());
  }
}
