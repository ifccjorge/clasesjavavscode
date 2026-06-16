package com.ejemplo;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import java.util.Set;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.ejemplo.entities.Correo;
import com.ejemplo.entities.Departamento;
import com.ejemplo.entities.Empleado;
import com.ejemplo.entities.Telefono;
import com.ejemplo.model.Genero;
import com.ejemplo.services.DepartamentoService;
import com.ejemplo.services.EmpleadoService;

import lombok.RequiredArgsConstructor;

@SpringBootApplication
@RequiredArgsConstructor
public class CrudEmpleadosSpringMvcApplication implements CommandLineRunner {

  private final EmpleadoService empleadoService;
  private final DepartamentoService departamentoService;
  // private final CorreoService correoService;
  // private final TelefonoService telefonoService;

	public static void main(String[] args) {
		SpringApplication.run(CrudEmpleadosSpringMvcApplication.class, args);
	}

  @Override
  public void run(String... args) throws Exception {
    // Departamentos
    Departamento departamento1 = Departamento.builder().nombre("RRHH").build();
    Departamento departamento2 = Departamento.builder().nombre("IT").build();
    Departamento departamento3 = Departamento.builder().nombre("Marketing").build();
    Departamento departamento4 = Departamento.builder().nombre("Ventas").build();
    Departamento departamento5 = Departamento.builder().nombre("Informática").build();
    departamentoService.saveDepartamento(departamento1);
    departamentoService.saveDepartamento(departamento2);
    departamentoService.saveDepartamento(departamento3);
    departamentoService.saveDepartamento(departamento4);
    departamentoService.saveDepartamento(departamento5);
    // Empleados
    Telefono telefono1 = Telefono.builder().numero("5550101").build();
    Telefono telefono2 = Telefono.builder().numero("5550102").build();
    Telefono telefono3 = Telefono.builder().numero("5550103").build();
    Correo correo1 = Correo.builder().email("a1@server.net").build();
    Empleado empleado1 = Empleado.builder()
    .nombre("Pedro")
    .primerApellido("Gómez")
    .segundoApellido("Rodríguez")
    .genero(Genero.HOMBRE)
    .fechaAlta(LocalDate.of(2021, Month.APRIL, 12))
    .salario(new BigDecimal(1550.25))
    .departamento(departamento1)
    .telefonos(Set.of(telefono1, telefono2, telefono3))
    .emails(Set.of(correo1))
    .build();
    //empleado1.setTelefonos(Set.of(telefono1, telefono2, telefono3));
    //empleado1.setEmail(Set.of(correo1));
    empleado1.getTelefonos().forEach(telefono -> telefono.setEmpleado(empleado1));
    empleado1.getEmails().forEach(correo -> correo.setEmpleado(empleado1));
    empleadoService.saveEmpleado(empleado1);
  }

}
