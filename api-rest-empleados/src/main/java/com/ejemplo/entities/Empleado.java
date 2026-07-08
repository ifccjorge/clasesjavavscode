package com.ejemplo.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import org.springframework.format.annotation.DateTimeFormat;

import com.ejemplo.models.Genero;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "empleados")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@ToString(exclude = { "emails", "telefonos" })
public class Empleado implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @NotNull(message = "El nombre no puede estar vacío")
  @NotBlank(message = "El nombre no puede contener espacios en blanco solamente")
  @Size(min = 4, max = 30, message = "El nombre debe tener entre 4 y 30 caracteres")
  @Pattern(regexp = "^([A-ZÁÉÍÓÚÑ][a-záéíóúñ]+(\s)?)+$", message = "La primera letra en mayusculas y solo letras de la A a la Z")
  private String nombre;
  
  private String primerApellido;

  private String segundoApellido;

  @DateTimeFormat(pattern = "yyyy-MM-dd")
  @PastOrPresent(message = "La fecha de alta no puede ser inferior a la fecha actual")
  private LocalDate fechaAlta;

  private BigDecimal salario;

  @Enumerated(EnumType.STRING)
  private Genero genero;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "departamento_id", nullable = false, foreignKey = @ForeignKey(name = "fk_empleado_departamento"))
  @JsonIgnoreProperties(ignoreUnknown = true, value = { "hibernateLazyInitializer", "handler" })
  private Departamento departamento;

  @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "empleado")
  @Builder.Default
  private Set<Telefono> telefonos = new HashSet<>();
  
  @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "empleado")
  @Builder.Default
  private Set<Correo> emails = new HashSet<>();

  private String imagenEmpleado;
}
