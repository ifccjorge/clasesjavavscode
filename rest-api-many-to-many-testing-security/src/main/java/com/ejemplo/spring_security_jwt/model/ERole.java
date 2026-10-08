package com.ejemplo.spring_security_jwt.model;

public enum ERole {

  ROLE_USER("user"),
  ROLE_ADMIN("admin");

  private final String rol;
  private ERole(String rol) { 
    this.rol = rol;
  }

  public void printRol() {
    System.out.println(rol);
  }

}
