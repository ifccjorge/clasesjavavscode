package com.ejemplo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ejemplo.entities.Telefono;

public interface TelefonoDao extends JpaRepository<Telefono, Integer> {

}
