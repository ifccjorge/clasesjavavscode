package com.ejemplo.service;

import org.springframework.stereotype.Service;

import com.ejemplo.dao.ContactoDao;
import com.ejemplo.entity.Contacto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ContactoServiceImpl implements ContactoService {

    private final ContactoDao contactoDao;
    
    @Override
    public Contacto getContactoById(long id) {
      return contactoDao.findById(id).get();
    }

    @Override
    public void save(Contacto contacto) {
      contactoDao.save(contacto);
    }

}
