package com.ejemplo.service;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import com.ejemplo.dto.UsuarioContactoResponseDto;
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
  @Mapping(source = "contacto.mobileNumber", target = "mobDto", qualifiedByName = "maskPhone")
  @Mapping(source = "contacto.email", target = "emailIdDto")
  UsuarioResponseDto mapUsuarioToUsuarioResponseDtoDemo(Usuario usuario, Contacto contacto);

  @Mapping(source = "usuario.id", target = "idDto")
  @Mapping(source = "usuario.username", target = "nameDto")
  @Mapping(source = "usuario.password", target = "passwordDto", ignore = true)
  @Mapping(source = "usuario.dateOfBirth", target = "dobDto")
  @Mapping(source = "usuario.status", target = "statusDto", defaultValue = "INACTIVE")
  @Mapping(source = "usuario.contactos", target = "contactosDto")
  UsuarioContactoResponseDto mapUsuarioToUsuarioResponseDto(Usuario usuario);

  // Enmascaramiento
  @Named("maskPhone")
  static String getPhoneNumber(String phone) {
    if (phone.length() <= 4) return phone;
    return "****" + phone.substring(phone.length() - 4);
  }
}
