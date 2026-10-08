package com.ejemplo;

import java.util.ArrayList;
import java.util.List;

import com.ejemplo.entity.Tag;
import com.ejemplo.entity.Tutorial;

public class DatosPrueba {

  private final List<Tutorial> listaTutorials = new ArrayList<>();
  private final List<Tag> listaTags = new ArrayList<>();

  public DatosPrueba() {
    this.listaTutorials.add(
      Tutorial.builder()
        .titulo("Curso de inglés")
        .descripcion("Curso de inglés de nivel avanzado")
        .publicado(true)
        .build()
    );
    this.listaTutorials.add(
      Tutorial.builder()
        .titulo("Curso de francés")
        .descripcion("Curso de francés de nivel avanzado")
        .publicado(false)
        .build()
    );
    this.listaTags.add(Tag.builder().nombre("nota").build());
    this.listaTags.add(Tag.builder().nombre("aviso").build());
  }

  public List<Tutorial> getListaTutorials() {
    return this.listaTutorials;
  }

  public List<Tag> getListaTags() {
    return this.listaTags;
  }
}
