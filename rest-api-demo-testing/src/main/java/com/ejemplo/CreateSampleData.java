package com.ejemplo;

import java.math.BigDecimal;
import java.util.Set;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.ejemplo.entities.Presentacion;
import com.ejemplo.entities.Producto;
import com.ejemplo.services.PresentacionService;
import com.ejemplo.services.ProductoService;
import com.ejemplo.spring_security_jwt.model.ERole;
import com.ejemplo.spring_security_jwt.model.Role;
import com.ejemplo.spring_security_jwt.model.User;
import com.ejemplo.spring_security_jwt.repository.RoleRepository;
import com.ejemplo.spring_security_jwt.repository.UserRepository;

@Configuration
public class CreateSampleData {
  @SuppressWarnings("unused")
  CreateSampleData(RoleRepository roleRepository) {
  }

  @Bean
  @SuppressWarnings("unused")
  CommandLineRunner sampleData(ProductoService productoService, PresentacionService presentacionService, RoleRepository roleRepository, UserRepository userRepository) {
    return args -> {
      // Presentaciones
      presentacionService.save(Presentacion.builder().nombre("unidad").descripcion("por unidades").build());
      presentacionService.save(Presentacion.builder().nombre("decena").descripcion("por decenas").build());
      // Productos
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
      // Roles
      Role rolAdmin = Role.builder().name(ERole.ROLE_ADMIN).build();
      Role rolUser = Role.builder().name(ERole.ROLE_USER).build();
      roleRepository.save(rolAdmin);
      roleRepository.save(rolUser);
      // Usuarios
      userRepository.save(
        User.builder()
          .username("usuario")
          .email("usuario@server.net")
          .password("Temp2026")
          .roles(Set.of(rolUser))
          .build()
      );
      userRepository.save(
        User.builder()
          .username("testuser")
          .email("testuser@server.net")
          .password("mypassword")
          .roles(Set.of(rolUser))
          .build()
      );
      userRepository.save(
        User.builder()
          .username("tecnico")
          .email("tecnico@server.net")
          .password("Temp2026$")
          .roles(Set.of(rolAdmin, rolUser))
          .build()
      );
    };
  }
}
