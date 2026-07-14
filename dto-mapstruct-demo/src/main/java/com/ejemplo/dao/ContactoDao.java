package com.ejemplo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ejemplo.entity.Contacto;

public interface ContactoDao extends JpaRepository<Contacto,Long> {

}
