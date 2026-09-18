package com.ejemplo.entities;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
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
@Table(name = "presentaciones")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class Presentacion {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @NotNull(message = "La presentación tiene que tener un nombre")
  @NotEmpty(message = "El nombre de la presentación no puede estar vacío")
  @Size(min = 4, max = 25, message = "El nombre del producto no puede tener menos de 4 caracteres ni más de 25")
  private String nombre;

  @NotNull(message = "La presentación tiene que tener una descripcion")
  @NotEmpty(message = "La descripcion de la presentación no puede estar vacío")
  @Size(max = 30, message = "El nombre de la presentación no puede superar los 30 caracteres")
  private String descripcion;
  
  @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST, mappedBy = "presentacion")
  @JsonIgnore
  @JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
  private List<Producto> productos;
}
