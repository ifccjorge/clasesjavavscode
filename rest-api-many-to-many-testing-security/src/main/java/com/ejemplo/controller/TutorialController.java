package com.ejemplo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ejemplo.entity.Tutorial;
import com.ejemplo.exception.ResourceNotFoundException;
import com.ejemplo.repository.TutorialRepository;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class TutorialController {
  private final TutorialRepository tutorialRepository;

  // http://localhost:8080/api/tutorials
  @GetMapping("/tutorials")
  @PreAuthorize("hasRole('ROLE_ADMIN') || hasRole('ROLE_USER')")
  public ResponseEntity<List<Tutorial>> getAllTutorials(@RequestParam(required = false) String titulo) {
    List<Tutorial> tutorials = new ArrayList<>();
    if (titulo == null)
      tutorialRepository.findAll().forEach(tutorials::add);
    else
      tutorialRepository.findByTituloContaining(titulo).forEach(tutorials::add);
    if (tutorials.isEmpty()) {
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    return new ResponseEntity<>(tutorials, HttpStatus.OK);
  }

  // http://localhost:8080/api/tutorials/1
  @GetMapping("/tutorials/{id}")
  @PreAuthorize("hasRole('ROLE_ADMIN') || hasRole('ROLE_USER')")
  public ResponseEntity<Tutorial> getTutorialById(@PathVariable("id") long id) {
    Tutorial tutorial = tutorialRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Not found Tutorial with id = " + id));
    return new ResponseEntity<>(tutorial, HttpStatus.OK);
  }

  // http://localhost:8080/api/tutorials
  @PostMapping("/tutorials")
  @PreAuthorize("hasRole('ROLE_ADMIN')")
  public ResponseEntity<Tutorial> createTutorial(@RequestBody Tutorial tutorial) {
    Tutorial _tutorial = tutorialRepository.save(
      Tutorial.builder()
        .titulo(tutorial.getTitulo())
        .descripcion(tutorial.getDescripcion())
        .publicado(tutorial.isPublicado())
        .build()
    );
    return new ResponseEntity<>(_tutorial, HttpStatus.CREATED);
  }

  // http://localhost:8080/api/tutorials/1
  @PutMapping("/tutorials/{id}")
  @PreAuthorize("hasRole('ROLE_ADMIN')")
  public ResponseEntity<Tutorial> updateTutorial(@PathVariable("id") long id, @RequestBody Tutorial tutorial) {
    Tutorial _tutorial = tutorialRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Not found Tutorial with id = " + id));
    _tutorial.setTitulo(tutorial.getTitulo());
    _tutorial.setDescripcion(tutorial.getDescripcion());
    _tutorial.setPublicado(tutorial.isPublicado());
    return new ResponseEntity<>(tutorialRepository.save(_tutorial), HttpStatus.OK);
  }

  // http://localhost:8080/api/tutorials/1
  @DeleteMapping("/tutorials/{id}")
  @PreAuthorize("hasRole('ROLE_ADMIN')")
  public ResponseEntity<HttpStatus> deleteTutorial(@PathVariable("id") long id) {
    tutorialRepository.deleteById(id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

  // http://localhost:8080/api/tutorials
  @DeleteMapping("/tutorials")
  @PreAuthorize("hasRole('ROLE_ADMIN')")
  public ResponseEntity<HttpStatus> deleteAllTutorials() {
    tutorialRepository.deleteAll();
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

  // http://localhost:8080/api/tutorials/published
  @GetMapping("/tutorials/published")
  @PreAuthorize("hasRole('ROLE_ADMIN') || hasRole('ROLE_USER')")
  public ResponseEntity<List<Tutorial>> findByPublished() {
    List<Tutorial> tutorials = tutorialRepository.findByPublicado(true);
    if (tutorials.isEmpty()) {
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    return new ResponseEntity<>(tutorials, HttpStatus.OK);
  }
}
