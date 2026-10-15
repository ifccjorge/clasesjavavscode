package com.ejemplo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ejemplo.entities.Presentacion;

public interface PresentacionDao extends JpaRepository<Presentacion, Integer> {

}
