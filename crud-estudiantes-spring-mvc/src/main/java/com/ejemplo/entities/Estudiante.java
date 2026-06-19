package com.ejemplo.entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
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
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "estudiantes")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@ToString(exclude = { "emails", "telefonos" })
public class Estudiante implements Serializable {

  private static final Locale LOCAL = Locale.US;
  public static final String SEPARADOR = "\n";

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;
  private String nombre;
  private String primerApellido;
  private String segundoApellido;
  @Enumerated(EnumType.STRING)
  private Genero genero;
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private LocalDate fechaMatricula;
  @ManyToOne(fetch = FetchType.LAZY)
  @EqualsAndHashCode.Exclude
  private Facultad facultad;
  @OneToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE }, mappedBy = "estudiante")
  private Set<Telefono> telefonos;
  @OneToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE }, mappedBy = "estudiante")
  private Set<Correo> emails;

  public String fechaFormateada() {
    DateTimeFormatter formatters = DateTimeFormatter.ofPattern("EEEE, d 'de' MMMM 'de' uuuu", LOCAL);
    return this.fechaMatricula.format(formatters);
  }
}
