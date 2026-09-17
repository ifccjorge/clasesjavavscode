package com.example.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.ejemplo.application.port.out.FileStoragePort;
import com.ejemplo.application.port.out.TaskRepositoryPort;
import com.ejemplo.application.service.TaskService;

// Configuration en la capa de infraestructura donde están los bean que se crean cuando se levanta el contexto de spring
// Clase nueva @Configuration en infraestructura con un método @Bean y que construya TaskService pasándole los puertos por constructor
@Configuration
public class BeanConfiguration {
  @Bean
  @SuppressWarnings("unused")
  TaskService taskService(TaskRepositoryPort taskRepositoryPort, FileStoragePort fileStoragePort) {
    return new TaskService(taskRepositoryPort, fileStoragePort);
  }
}
