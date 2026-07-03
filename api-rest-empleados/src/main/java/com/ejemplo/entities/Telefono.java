package com.ejemplo.entities;


import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "telefonos")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@ToString(exclude = { "empleado" })
public class Telefono implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  private String numero;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(
    name = "empleado_id",
    nullable = false,
    foreignKey = @ForeignKey(name = "fk_telefono_empleado")
  )
  @JsonIgnore
  @JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
  private Empleado empleado;
  
}
