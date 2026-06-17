package com.ejemplo.entities;


import java.io.Serializable;

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

@Entity
@Table(name = "correos")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class Correo implements Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;
  private String email;
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(
    name = "empleado_id",
    nullable = false,
    foreignKey = @ForeignKey(name = "fk_correo_empleado")
  )
  private Empleado empleado;
}
