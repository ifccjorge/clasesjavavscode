package com.ejemplo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ejemplo.entity.Tutorial;

public interface TutorialRepository extends JpaRepository<Tutorial, Long> {
  List<Tutorial> findByPublicado(boolean publicado);

  List<Tutorial> findByTituloContaining(String titulo);

  List<Tutorial> findTutorialsByTagsId(Long tagId);
}
