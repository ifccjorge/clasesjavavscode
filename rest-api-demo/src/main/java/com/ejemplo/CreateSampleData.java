package com.ejemplo;

import java.math.BigDecimal;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.ejemplo.entities.Presentacion;
import com.ejemplo.entities.Producto;
import com.ejemplo.services.PresentacionService;
import com.ejemplo.services.ProductoService;

@Configuration
public class CreateSampleData {
  @Bean
  public CommandLineRunner sampleData(ProductoService productoService, PresentacionService presentacionService) {
    return args -> {
      presentacionService.save(Presentacion.builder().nombre("unidad").descripcion("por unidades").build());
      presentacionService.save(Presentacion.builder().nombre("decena").descripcion("por decenas").build());
      productoService.save(
        Producto.builder()
          .nombre("rezma de papel")
          .descripcion("Descripción")
          .precio(new BigDecimal(3.75))
          .existencias(10)
          .presentacion(presentacionService.findById(1))
          .build()
      );
      productoService.save(
        Producto.builder()
          .nombre("cartas")
          .descripcion("Description")
          .precio(new BigDecimal(1))
          .existencias(10)
          .presentacion(presentacionService.findById(2))
          .build()
      );
      productoService.save(
        Producto.builder()
          .nombre("guitarra de juguete")
          .descripcion("Description")
          .precio(new BigDecimal(4.5))
          .existencias(5)
          .presentacion(presentacionService.findById(1))
          .build()
      );
      productoService.save(
        Producto.builder()
          .nombre("teclado de computadora")
          .descripcion("Description")
          .precio(new BigDecimal(15))
          .existencias(5)
          .presentacion(presentacionService.findById(1))
          .build()
      );
      productoService.save(
        Producto.builder()
          .nombre("teclado para laptop")
          .descripcion("Description")
          .precio(new BigDecimal(40))
          .existencias(5)
          .presentacion(presentacionService.findById(1))
          .build()
      );

      productoService.save(
        Producto.builder()
          .nombre("altavoces bluetooth")
          .descripcion("Description")
          .precio(new BigDecimal(15))
          .existencias(5)
          .presentacion(presentacionService.findById(1))
          .build()
      );
      productoService.save(
        Producto.builder()
          .nombre("lapices 2b")
          .descripcion("Description")
          .precio(new BigDecimal(1.50))
          .existencias(4)
          .presentacion(presentacionService.findById(2))
          .build()
      );
      productoService.save(
        Producto.builder()
          .nombre("boligrafos")
          .descripcion("de color azul")
          .precio(new BigDecimal(2))
          .existencias(10)
          .presentacion(presentacionService.findById(1))
          .build()
      );
      productoService.save(
        Producto.builder()
          .nombre("monitor de 15 pulgadas")
          .descripcion("Description")
          .precio(new BigDecimal(40))
          .existencias(5)
          .presentacion(presentacionService.findById(1))
          .build()
      );
      productoService.save(
        Producto.builder()
          .nombre("cargador de movil")
          .descripcion("para telefono samsung")
          .precio(new BigDecimal(10))
          .existencias(10)
          .presentacion(presentacionService.findById(1))
          .build()
      );
      productoService.save(
        Producto.builder()
          .nombre("mouse")
          .descripcion("ratón de Apple")
          .precio(new BigDecimal(40))
          .existencias(100)
          .presentacion(presentacionService.findById(1))
          .build()
      );
    };
  }
}
