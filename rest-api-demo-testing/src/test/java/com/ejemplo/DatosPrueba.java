package com.ejemplo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.ejemplo.entities.Presentacion;
import com.ejemplo.entities.Producto;

public class DatosPrueba {

  private final List<Producto> listaProductos = new ArrayList<>();

  public DatosPrueba() {
    Presentacion presentacionPorUndades = Presentacion.builder()
      .nombre("Unidad")
      .descripcion("Por unidades")
      .build();
    Presentacion presentacionPorDecenas = Presentacion.builder()
      .nombre("Decenas")
      .descripcion("Por decenas")
      .build();
    Producto producto1 = Producto.builder()
      .nombre("Google Pixel 11 Pro")
      .descripcion("Google Smart Phone")
      .precio(new BigDecimal(900))
      .existencias(5)
      .presentacion(presentacionPorUndades)
      .build();
    Producto producto2 = Producto.builder()
      .nombre("Tornillos fijadores")
      .descripcion("Tornillos fijadores de pared")
      .precio(new BigDecimal(2.5))
      .existencias(50)
      .presentacion(presentacionPorDecenas)
      .build();
    this.listaProductos.add(producto1);
    this.listaProductos.add(producto2);
  }

  public List<Producto> getListaProductos() {
    return this.listaProductos;
  }
}
