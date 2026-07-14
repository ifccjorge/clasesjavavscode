package com.ejemplo.service;

import java.time.format.DateTimeFormatter;

import org.springframework.stereotype.Service;

import com.ejemplo.dao.UsuarioDao;
import com.ejemplo.dto.UsuarioResponse;
import com.ejemplo.entity.Usuario;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UsuarioServiceImpl implements UsuarioService {
  private final UsuarioDao usuarioDao;
  @Override
  public UsuarioResponse getUsuarioById(long id) {
    UsuarioResponse usuarioResponse;
    // Mapeo manual entre la entidad Usuario y el DTO UsuarioResponse
    Usuario usuario = usuarioDao.findById(id).orElseThrow(() -> new RuntimeException("User not found!!!"));
    usuarioResponse = new UsuarioResponse(
      usuario.getId(), 
      usuario.getUsername(), 
      usuario.getPassword(), 
      DateTimeFormatter.ISO_LOCAL_DATE.format(usuario.getDateOfBirth())
    );
    return usuarioResponse;
  }
}
