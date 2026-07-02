package com.ejemplo.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import com.ejemplo.model.Genero;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "profesores")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@ToString
public class Profesor implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  private String nombre;

  private String primerApellido;

  private String segundoApellido;

  @Enumerated(EnumType.STRING)
  private Genero genero;

  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private LocalDate fechaAlta;

  private BigDecimal salario;

  private String foto;

  @ManyToOne(fetch = FetchType.LAZY)
  private Facultad facultad;

}
