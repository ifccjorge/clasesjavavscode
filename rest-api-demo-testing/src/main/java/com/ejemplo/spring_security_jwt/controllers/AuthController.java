package com.ejemplo.spring_security_jwt.controllers;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ejemplo.spring_security_jwt.model.ERole;
import com.ejemplo.spring_security_jwt.model.Role;
import com.ejemplo.spring_security_jwt.model.User;
import com.ejemplo.spring_security_jwt.payload.request.LoginRequest;
import com.ejemplo.spring_security_jwt.payload.request.SignupRequest;
import com.ejemplo.spring_security_jwt.payload.response.JwtResponse;
import com.ejemplo.spring_security_jwt.payload.response.MessageResponse;
import com.ejemplo.spring_security_jwt.repository.RoleRepository;
import com.ejemplo.spring_security_jwt.repository.UserRepository;
import com.ejemplo.spring_security_jwt.security.jwt.JwtUtils;
import com.ejemplo.spring_security_jwt.security.service.UserDetailsImpl;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

  private final AuthenticationManager authenticationManager;
  private final UserRepository userRepository;
  private final RoleRepository roleRepository;
  private final PasswordEncoder encoder;
  private final JwtUtils jwtUtils;

  private static final Logger LOGGER = LoggerFactory.getLogger(AuthController.class);

  // Método para registrar un usuario
  // http://localhost:8080/api/auth/signup
  @PostMapping("/signup")
  public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signupRequest, BindingResult validationResults) {
    // Existen los valores
    if (userRepository.existsByUsername(signupRequest.getUsername())) {
      return ResponseEntity.badRequest().body(new MessageResponse("Error: Username is already taken"));
    }
    if (userRepository.existsByEmail(signupRequest.getEmail())) {
      return ResponseEntity.badRequest().body(new MessageResponse("Error: Email is already in use!!!"));
    }
    // Crear el usuario, es decir, el User para guardarlo en la tabla de User, con
    // las propiedades del JSON recibido en la peticion (signupRequest)
    String usuario = signupRequest.getUsername();
    User user = User.builder()
      .username(usuario)
      .email(signupRequest.getEmail())
      .password(encoder.encode(signupRequest.getPassword()))
      .build();
    // Gestión de roles
    Set<String> strRoles = signupRequest.getRole();
    Set<Role> roles = new HashSet<>();
    if (strRoles == null) {
      Role userRole = roleRepository.findByName(ERole.ROLE_USER)
        .orElseThrow(() -> new RuntimeException("Error: Role not found"));
        roles.add(userRole);
    } else {
      strRoles.forEach(role -> {
        switch (role) {
          case "admin" -> {
            Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN).orElseThrow(() -> new RuntimeException("Error: Role is not found"));
            roles.add(adminRole);
          }
          default -> {
            Role userRole = roleRepository.findByName(ERole.ROLE_USER).orElseThrow(() -> new RuntimeException("Error: Role not found")); 
            roles.add(userRole);
          }
        }
      });
    }
    user.setRoles(roles);
    userRepository.save(user);
    // Usuario creado
    LOGGER.info("Usuario {} creado", usuario);
    return ResponseEntity.ok(new MessageResponse("User " + usuario + " registered successfully"));
  }

  // Metodo para logearse un usuario que se ha registrado previamente
  // http://localhost:8080/api/auth/signin
  @PostMapping("/signin")
  public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest, BindingResult result) {
    // Validar el JSON recibido en el cuerpo de la peticion.
    Authentication authentication = authenticationManager
      .authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
    // Genera token
    SecurityContextHolder.getContext().setAuthentication(authentication);
    String jwt = jwtUtils.generateJwtToken(authentication);
    // Datos de usuario y roles
    UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
    List<String> roles = userDetails.getAuthorities().stream()
      .map(item -> item.getAuthority())
      .collect(Collectors.toList());
    // Mostrar por la consola los roles
    LOGGER.info("Roles del usuario {}: {}", userDetails.getUsername(), roles);
    // Resultado correcto
    return ResponseEntity.ok(
      new JwtResponse(
        jwt,
        userDetails.getId(),
        userDetails.getUsername(),
        userDetails.getEmail(),
        roles
      )
    );
  }

  // Todos los usuarios: http://localhost:8080/api/auth/users
  @GetMapping("/users")
  @PreAuthorize("hasRole('ROLE_ADMIN')")
  @Transactional
  public ResponseEntity<?> registerUser2() {
    List<User> allUsers = userRepository.findAllUsers();
    List<JwtResponse> allUsersJwtResponse = new ArrayList<>();
    allUsers.stream().forEach(
      user -> allUsersJwtResponse.add(
        new JwtResponse(
          null,
          user.getId(),
          user.getUsername(),
          user.getEmail(),
          user.getRoles().stream().map(r -> r.getName().name()).toList()
        )
      )
    );
    // Usuario creado
    LOGGER.info("Usuarios encontrados: {}", allUsers.size());
    return ResponseEntity.ok(allUsersJwtResponse);
  }

}