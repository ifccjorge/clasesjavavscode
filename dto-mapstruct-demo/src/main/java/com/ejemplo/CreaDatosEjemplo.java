package com.ejemplo;

import java.time.LocalDate;
import java.time.Month;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.ejemplo.entity.Contacto;
import com.ejemplo.entity.Usuario;
import com.ejemplo.service.ContactoService;
import com.ejemplo.service.UsuarioService;

@Configuration
public class CreaDatosEjemplo {
  @Bean
  public CommandLineRunner sampleData(UsuarioService usuarioService, ContactoService contactoService) {
    return args -> {
      usuarioService.save(
        Usuario.builder()
          .username("user1")
          .password("Temp")
          .dateOfBirth(LocalDate.of(2000, Month.FEBRUARY, 1))
          .status("activo")
          .build()
      );
      contactoService.save(
        Contacto.builder()
          .mobileNumber("5550123")
          .email("a1@server.net")
          .build()
      );
    };
  }


}
