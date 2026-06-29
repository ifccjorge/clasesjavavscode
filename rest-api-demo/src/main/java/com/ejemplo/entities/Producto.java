package com.ejemplo.entities;

import java.math.BigDecimal;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "productos")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class Producto {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @NotNull(message = "El producto tiene que tener un nombre")
  @NotEmpty(message = "El nombre del procto no puede estar vacío")
  @Size(min = 4, max = 25, message = "El nombre del producto no puede tener menos de 4 caracteres ni más de 25")
  private String nombre;

  @NotNull(message = "El producto tiene que tener una descripcion")
  @NotEmpty(message = "La descripcion del producto no puede estar vacío")
  @Size(max = 25, message = "El nombre del producto no puede superar los 25 caracteres")
  private String descripcion;

  @Min(value = 0, message = "Las existencias no pueden ser negativas")
  private int existencias;

  @Min(value = 0, message = "El precio no puede ser negativo")
  private BigDecimal precio;
  
  @NotNull(message = "La presentación del producto es requerida")
  @ManyToOne(fetch = FetchType.LAZY)
  private Presentacion presentacion;
}