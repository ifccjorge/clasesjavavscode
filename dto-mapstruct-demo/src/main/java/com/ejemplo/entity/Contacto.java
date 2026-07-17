package com.ejemplo.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Access;
import jakarta.persistence.AccessType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "contactos")
@Access(AccessType.FIELD)
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Contacto implements Serializable {
  private static final long serialVersionUID = 1L;
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "user_id")
  private long userId;
  @Column(name = "mobile_number")
  private String mobileNumber;
  @Column(name = "email_id")
  private String email;

  @Builder.Default
  @ManyToMany(fetch = FetchType.LAZY, mappedBy = "contactos")
  @JsonIgnore
  private Set<Usuario> usuarios = new HashSet<>();

  // Copia objeto
  public Contacto(Contacto contacto) {
    this(contacto.userId, contacto.mobileNumber, contacto.email, contacto.usuarios);
  }
}
