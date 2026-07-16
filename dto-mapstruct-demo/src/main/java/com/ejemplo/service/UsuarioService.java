package com.ejemplo.service;

import com.ejemplo.dto.UsuarioContactoResponseDto;
import com.ejemplo.entity.Usuario;

public interface UsuarioService {
  UsuarioContactoResponseDto getUsuarioById(long id);
  void save(Usuario usuario);
}
