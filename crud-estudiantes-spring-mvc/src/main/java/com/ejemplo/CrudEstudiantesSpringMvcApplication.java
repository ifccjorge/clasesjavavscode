package com.ejemplo;

import java.time.LocalDate;
import java.time.Month;
import java.util.Set;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.ejemplo.entities.Correo;
import com.ejemplo.entities.Estudiante;
import com.ejemplo.entities.Facultad;
import com.ejemplo.entities.Telefono;
import com.ejemplo.model.Genero;
import com.ejemplo.services.EstudianteService;
import com.ejemplo.services.FacultadService;

import lombok.RequiredArgsConstructor;

@SpringBootApplication
@RequiredArgsConstructor
public class CrudEstudiantesSpringMvcApplication implements CommandLineRunner {

  private final EstudianteService estudianteService;
  private final FacultadService facultadService;

	public static void main(String[] args) {
		SpringApplication.run(CrudEstudiantesSpringMvcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
    // Facultades
    Facultad facultad1 = Facultad.builder().nombre("Literatura").build();
    Facultad facultad2 = Facultad.builder().nombre("Química").build();
    Facultad facultad3 = Facultad.builder().nombre("Derecho").build();
    Facultad facultad4 = Facultad.builder().nombre("Geología").build();
    Facultad facultad5 = Facultad.builder().nombre("Filosofía").build();
    facultadService.saveFacultad(facultad1);
    facultadService.saveFacultad(facultad2);
    facultadService.saveFacultad(facultad3);
    facultadService.saveFacultad(facultad4);
    facultadService.saveFacultad(facultad5);
    // Teléfonos
    Telefono telefono1 = Telefono.builder().numero("5550101").build();
    Telefono telefono2 = Telefono.builder().numero("5550102").build();
    Telefono telefono3 = Telefono.builder().numero("5550103").build();
    Telefono telefono4 = Telefono.builder().numero("5550104").build();
    Telefono telefono5 = Telefono.builder().numero("5550105").build();
    // Correos
    Correo correo1 = Correo.builder().email("a1@server.net").build();
    Correo correo2 = Correo.builder().email("b1@server.net").build();
    Correo correo3 = Correo.builder().email("b2@server.net").build();
    // Estudiantes
    Estudiante estudiante1 = Estudiante.builder()
      .nombre("Pedro")
      .primerApellido("Gómez")
      .segundoApellido("Rodríguez")
      .genero(Genero.HOMBRE)
      .fechaMatricula(LocalDate.of(2021, Month.APRIL, 12))
      .facultad(facultad1)
      .telefonos(Set.of(telefono1, telefono2, telefono3))
      .emails(Set.of(correo1))
      .build();
    Estudiante estudiante2 = Estudiante.builder()
      .nombre("Luisa")
      .primerApellido("Montero")
      .segundoApellido("Díaz")
      .genero(Genero.MUJER)
      .fechaMatricula(LocalDate.of(2023, Month.SEPTEMBER, 1))
      .facultad(facultad2)
      .telefonos(Set.of(telefono4, telefono5))
      .emails(Set.of(correo2, correo3))
      .build();
    estudiante1.getEmails().forEach(telefono -> telefono.setEstudiante(estudiante1));
    estudiante1.getEmails().forEach(correo -> correo.setEstudiante(estudiante1));
    estudiante2.getTelefonos().forEach(telefono -> telefono.setEstudiante(estudiante2));
    estudiante2.getTelefonos().forEach(correo -> correo.setEstudiante(estudiante2));
    estudianteService.saveEstudiante(estudiante1);
    estudianteService.saveEstudiante(estudiante2);
	}

}
