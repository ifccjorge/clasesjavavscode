package com.ejemplo.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.format.annotation.DateTimeFormat;

import com.ejemplo.model.Genero;

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

  private static final Locale LOCAL = Locale.of("es", "ES");
  public static final String SEPARADOR = "\n";

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

  @Enumerated(EnumType.STRING)
  private Genero genero;

  @DateTimeFormat(pattern = "yyyy-MM-dd")
  @PastOrPresent(message = "La fecha de alta no puede ser inferior a la fecha actual")
  private LocalDate fechaAlta;
  private BigDecimal salario;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "departamento_id", nullable = false, foreignKey = @ForeignKey(name = "fk_empleado_departamento"))
  private Departamento departamento;

  @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "empleado")
  private Set<Telefono> telefonos;
  
  @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "empleado")
  private Set<Correo> emails;

  private String foto;

  public String salarioMoneda() {
    NumberFormat numberFormat = NumberFormat.getCurrencyInstance(LOCAL);
    numberFormat.setMinimumFractionDigits(2);
    numberFormat.setMaximumFractionDigits(2);
    return numberFormat.format(this.salario);
  }

  public String fechaFormateada() {
    DateTimeFormatter formatters = DateTimeFormatter.ofPattern("EEEE, d 'de' MMMM 'de' uuuu", LOCAL);
    return this.fechaAlta.format(formatters);
  }

  public String correosSeparador() {
    return this.emails == null ? "" : String.join(SEPARADOR, this.emails.stream().map(Correo::getEmail).toList());
  }

  public String telefonosSeparador() {
    return this.telefonos == null ? "" : this.telefonos.stream().collect(Collectors.mapping(Telefono::getNumero, Collectors.joining(SEPARADOR)));
  }
}
