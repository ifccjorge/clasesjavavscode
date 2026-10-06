package com.ejemplo;

import java.util.Set;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.ejemplo.entity.Tag;
import com.ejemplo.entity.Tutorial;
import com.ejemplo.repository.TagRepository;
import com.ejemplo.repository.TutorialRepository;
import com.ejemplo.spring_security_jwt.model.ERole;
import com.ejemplo.spring_security_jwt.model.Role;
import com.ejemplo.spring_security_jwt.model.User;
import com.ejemplo.spring_security_jwt.repository.RoleRepository;
import com.ejemplo.spring_security_jwt.repository.UserRepository;

@Configuration
public class CreateSampleData {
  private final PasswordEncoder passwordEncoder;

  @SuppressWarnings("unused")
  CreateSampleData(
    RoleRepository roleRepository,
    PasswordEncoder passwordEncoder
  ) {
    this.passwordEncoder = passwordEncoder;
  }

  @Bean
  @SuppressWarnings("unused")
  CommandLineRunner sampleData(
    TutorialRepository tutorialRepository,
    TagRepository tagRepository,
    RoleRepository roleRepository,
    UserRepository userRepository
  ) {
    return args -> {
      // Tags
      tagRepository.save(Tag.builder().nombre("nota").build());
      tagRepository.save(Tag.builder().nombre("aviso").build());
      // Tutoriales
      tutorialRepository.save(
        Tutorial.builder()
          .titulo("Curso de inglés")
          .descripcion("Curso de inglés de nivel avanzado")
          .publicado(true)
          .build()
      );
      tutorialRepository.save(
        Tutorial.builder()
          .titulo("Curso de francés")
          .descripcion("Curso de francés de nivel avanzado")
          .publicado(true)
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
          .password(passwordEncoder.encode("usuario"))
          .roles(Set.of(rolUser))
          .build()
      );
      userRepository.save(
        User.builder()
          .username("tecnico")
          .email("tecnico@server.net")
          .password(passwordEncoder.encode("Temp2026$"))
          .roles(Set.of(rolAdmin))
          .build()
      );
    };
  }
}
