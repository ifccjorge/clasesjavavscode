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
  public CreateSampleData(
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
      Tag tag1 = Tag.builder().nombre("nota").build();
      Tag tag2 = Tag.builder().nombre("aviso").build();
      Tag tag3 = Tag.builder().nombre("recomendación").build();
      Tag tag4 = Tag.builder().nombre("información").build();
      tagRepository.save(tag1);
      tagRepository.save(tag2);
      tagRepository.save(tag3);
      tagRepository.save(tag4);
      // Tutoriales
      Tutorial tutorial1 = Tutorial.builder()
        .titulo("Curso de inglés")
        .descripcion("Curso de inglés de nivel avanzado")
        .publicado(true)
        .build();
      Tutorial tutorial2 = Tutorial.builder()
        .titulo("Curso de francés")
        .descripcion("Curso de francés de nivel avanzado")
        .publicado(true)
        .build();
      tutorialRepository.save(tutorial1);
      tutorialRepository.save(tutorial2);
      // Relaciones entre entidades
      tutorial1.addTag(tag1);
      tutorial1.addTag(tag2);
      tutorial1.addTag(tag3);
      tutorial2.addTag(tag2);
      tutorial2.addTag(tag3);
      tutorial2.addTag(tag4);
      // Relaciones persistidas
      tagRepository.save(tag1);
      tagRepository.save(tag2);
      tagRepository.save(tag3);
      tagRepository.save(tag4);
      tutorialRepository.save(tutorial1);
      tutorialRepository.save(tutorial2);
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
