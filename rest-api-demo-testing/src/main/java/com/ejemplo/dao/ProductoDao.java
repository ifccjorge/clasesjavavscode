package com.ejemplo.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ejemplo.entities.Producto;

public interface ProductoDao extends JpaRepository<Producto, Integer> {

  // Recupera los productos paginados
  @Override
  @Query(value = "select p from Producto p left join fetch p.presentacion", countQuery = "select count(p) from Producto p left join p.presentacion")
  public Page<Producto> findAll(Pageable pageable);

  // Recupera los productos ordenados sin paginación
  @Override
  @Query(value = "select p from Producto p left join fetch p.presentacion")
  public List<Producto> findAll(Sort sort);

  // Recupera un producto
  @Query(value = "select p from Producto p left join fetch p.presentacion where p.id = :id")
  public Producto findById(int id);
}
