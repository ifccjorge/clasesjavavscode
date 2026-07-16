package com.ejemplo.service;

import org.springframework.stereotype.Service;

import com.ejemplo.dao.UsuarioDao;
import com.ejemplo.dto.UsuarioContactoResponseDto;
import com.ejemplo.entity.Usuario;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UsuarioServiceImpl implements UsuarioService {
  private final UsuarioDao usuarioDao;
  private final UsuarioMapper usuarioMapper;
  @Override
  public UsuarioContactoResponseDto getUsuarioById(long id) {
    UsuarioContactoResponseDto usuarioContactoResponseDto;
    Usuario usuario = usuarioDao.findById(id).orElseThrow(() -> new RuntimeException("User not found!!!"));
    //Contacto contacto = contactoDao.findById(id).orElseThrow(() -> new RuntimeException("Contact not found!!!"));
    // Mapeo manual entre la entidad Usuario y el DTO UsuarioResponse
    //usuarioResponseDto = new UsuarioResponseDto(
    //  usuario.getId(), 
    //  usuario.getUsername(), 
    //  usuario.getPassword(), 
    //  DateTimeFormatter.ISO_LOCAL_DATE.format(usuario.getDateOfBirth())
    //);
    // Mapeo con mapstruct
    usuarioContactoResponseDto = usuarioMapper.mapUsuarioToUsuarioResponseDto(usuario);
    return usuarioContactoResponseDto;
  }
  @Override
  public void save(Usuario usuario) {
    usuarioDao.save(usuario);
  }
}
