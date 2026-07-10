package com.ejemplo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ejemplo.entity.Tutorial;
import com.ejemplo.exception.ResourceNotFoundException;
import com.ejemplo.repository.TutorialRepository;

@RestController
@RequestMapping("/api")
public class TutorialController {
  private final TutorialRepository tutorialRepository = null;

  @GetMapping("/tutorials")
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

  @GetMapping("/tutorials/{id}")
  public ResponseEntity<Tutorial> getTutorialById(@PathVariable("id") long id) {
    Tutorial tutorial = tutorialRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Not found Tutorial with id = " + id));
    return new ResponseEntity<>(tutorial, HttpStatus.OK);
  }

  @PostMapping("/tutorials")
  public ResponseEntity<Tutorial> createTutorial(@RequestBody Tutorial tutorial) {
    Tutorial _tutorial = tutorialRepository.save(
      Tutorial.builder()
      .titulo(tutorial.getTitulo())
      .descripcion(tutorial.getDescripcion())
      .publicado(true)
      .build()
    );
    return new ResponseEntity<>(_tutorial, HttpStatus.CREATED);
  }
}
