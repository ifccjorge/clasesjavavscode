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
    Usuario usuario1 = Usuario.builder()
      .username("user1")
      .password("Temp01")
      .dateOfBirth(LocalDate.of(2000, Month.FEBRUARY, 1))
      .status(null)
      .build();
    Contacto contacto1 = Contacto.builder()
      .mobileNumber("5550123")
      .email("a1@server.net")
      .build();
    Usuario usuario2 = Usuario.builder()
      .username("user2")
      .password("Temp02")
      .dateOfBirth(LocalDate.of(2003, Month.DECEMBER, 10))
      .status(null)
      .build();
    Contacto contacto2 = Contacto.builder()
      .mobileNumber("5550150")
      .email("b1@server.net")
      .build();
    return args -> {
      usuarioService.save(usuario1);
      usuarioService.save(usuario2);
      contactoService.save(contacto1);
      contactoService.save(contacto2);
    };
  }


}
