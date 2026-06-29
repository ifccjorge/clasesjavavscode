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
    };
  }
}
