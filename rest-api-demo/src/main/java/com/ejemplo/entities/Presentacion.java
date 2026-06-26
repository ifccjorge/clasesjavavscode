package com.ejemplo.entities;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
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
  private String nombre;
  private String descripcion;
  @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST, mappedBy = "presentacion")
  private List<Producto> productos;
}
