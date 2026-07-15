package com.ejemplo.service;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.ejemplo.dto.UsuarioResponseDto;
import com.ejemplo.entity.Contacto;
import com.ejemplo.entity.Usuario;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {
  @Mapping(source = "usuario.id", target = "idDto")
  @Mapping(source = "usuario.username", target = "nameDto")
  @Mapping(source = "usuario.password", target = "passwordDto", ignore = true)
  @Mapping(source = "usuario.dateOfBirth", target = "dobDto")
  @Mapping(source = "usuario.status", target = "statusDto", defaultValue = "INACTIVE")
  @Mapping(source = "contacto.mobileNumber", target = "mobDto")
  @Mapping(source = "contacto.email", target = "emailIdDto")
  UsuarioResponseDto mapUsuarioToUsuarioResponseDto(Usuario usuario, Contacto contacto);
}
