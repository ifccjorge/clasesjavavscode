package com.ejemplo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ejemplo.dto.UsuarioResponseDto;
import com.ejemplo.service.UsuarioService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(path = "/users")
@RequiredArgsConstructor
public class UsuarioController {
  private final UsuarioService usuarioService;
  // Metodo que devuelve un usuario por el id
  @GetMapping(path = "/{id}")
  public UsuarioResponseDto getUsuarioById(@PathVariable long id) {
    return usuarioService.getUsuarioById(id);
  }
}
