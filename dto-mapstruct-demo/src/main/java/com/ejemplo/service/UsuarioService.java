package com.ejemplo.service;

import com.ejemplo.dto.UsuarioResponseDto;
import com.ejemplo.entity.Usuario;

public interface UsuarioService {
  UsuarioResponseDto getUsuarioById(long id);
  void save(Usuario usuario);
}
