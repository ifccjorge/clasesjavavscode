package com.ejemplo.service;

import com.ejemplo.dto.UsuarioResponse;

public interface UsuarioService {
  UsuarioResponse getUsuarioById(long id);
}
