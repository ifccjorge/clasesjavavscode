package com.ejemplo.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

import org.springframework.format.annotation.DateTimeFormat;

import com.ejemplo.model.Genero;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="empleados")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class Empleado implements Serializable {
  @Id
  @GeneratedValue(strategy=GenerationType.IDENTITY)
  private int id;
  private String nombre;
  private String primerApellido;
  private String segundoApellido;
  @Enumerated(EnumType.STRING)
  private Genero genero;
  @DateTimeFormat(pattern="yyyy-MM-dd")
  //@DateTimePattern(pattern="yyyy-MM-dd")
  private LocalDate fechaAlta;
  private BigDecimal salario;
  @ManyToOne(fetch=FetchType.LAZY)
  private Departamento departamento;
  @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, mappedBy = "empleado")
  private Set<Telefono> telefono;
  @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, mappedBy = "empleado")
  private Set<Correo> emails;
}
