package com.ejemplo;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.ejemplo.entities.Correo;
import com.ejemplo.entities.Departamento;
import com.ejemplo.entities.Empleado;
import com.ejemplo.entities.Telefono;
import com.ejemplo.model.Genero;
import com.ejemplo.services.CorreoService;
import com.ejemplo.services.DepartamentoService;
import com.ejemplo.services.EmpleadoService;
import com.ejemplo.services.TelefonoService;

@Configuration
public class CreateSampleData {
  @Bean
  public CommandLineRunner sampleData(
    EmpleadoService empleadoService,
    DepartamentoService departamentoService,
    TelefonoService telefonoService,
    CorreoService correoService
  ) {
    return args -> {
      // Departamentos
      departamentoService.save(Departamento.builder().nombre("RRHH").build());
      departamentoService.save(Departamento.builder().nombre("IT").build());
      departamentoService.save(Departamento.builder().nombre("Marketing").build());
      departamentoService.save(Departamento.builder().nombre("Ventas").build());
      departamentoService.save(Departamento.builder().nombre("Informática").build());
      empleadoService.save(
        Empleado.builder()
        .nombre("Pedro")
          .primerApellido("Gómez")
          .segundoApellido("Rodríguez")
          .fechaAlta(LocalDate.of(2021, Month.APRIL, 12))
          .salario(new BigDecimal(1550.25))
          .genero(Genero.HOMBRE)
          .departamento(departamentoService.findById(1))
          .build()
      );
      // Empleados
      empleadoService.save(
        Empleado.builder()
          .nombre("Luisa")
          .primerApellido("Montero")
          .segundoApellido("Díaz")
          .fechaAlta(LocalDate.of(2023, Month.SEPTEMBER, 1))
          .salario(new BigDecimal(1850.75))
          .genero(Genero.MUJER)
          .departamento(departamentoService.findById(5))
          .build()
      );
      // Teléfonos
      telefonoService.save(Telefono.builder().numero("5550101").empleado(empleadoService.findById(1)).build());
      telefonoService.save(Telefono.builder().numero("5550102").empleado(empleadoService.findById(1)).build());
      telefonoService.save(Telefono.builder().numero("5550103").empleado(empleadoService.findById(1)).build());
      telefonoService.save(Telefono.builder().numero("5550104").empleado(empleadoService.findById(2)).build());
      telefonoService.save(Telefono.builder().numero("5550105").empleado(empleadoService.findById(2)).build());
      // Correos
      correoService.save(Correo.builder().email("a1@server.net").empleado(empleadoService.findById(1)).build());
      correoService.save(Correo.builder().email("b1@server.net").empleado(empleadoService.findById(2)).build());
      correoService.save(Correo.builder().email("b2@server.net").empleado(empleadoService.findById(2)).build());

    };
  }
}
