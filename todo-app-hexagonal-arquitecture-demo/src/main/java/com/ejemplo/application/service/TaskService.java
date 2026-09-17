// Implementa el caso de uso
package com.ejemplo.application.service;

import java.util.List;

import com.ejemplo.application.port.in.CreateTaskUseCase;
import com.ejemplo.application.port.in.DeleteTaskUseCase;
import com.ejemplo.application.port.in.GetTaskUseCase;
import com.ejemplo.application.port.in.ListTaskUseCase;
import com.ejemplo.application.port.in.UpdateTaskUseCase;
import com.ejemplo.application.port.in.UploadTaskImageUseCase;
import com.ejemplo.application.port.out.FileStoragePort;
import com.ejemplo.application.port.out.TaskRepositoryPort;
import com.ejemplo.domain.exception.TaskNotFoundException;
import com.ejemplo.domain.model.Task;

import lombok.RequiredArgsConstructor;

/* ¿Es correcta una anotacion de Spring aqui? 
 * 
 * Los mas puristas dirian que NO, pero tiene un coste implementar esto
 * correctamente.
 * 
 * Con esta anotacion estamos introduciendo una dependencia del framework
 * en la capa de aplicacion, y el problema es que si mañana migramos a quarkus
 * o cualquier otro framework o si queremos testear el caso de uso en aislamiento total, esta clase
 * ya no seria agnostica del framework, es decir, estaria acoplada el Spring Framework
 * 
 * ¿Que deberia hacerse para que este acoplamiento no existiera?
 * 
 * Rta. Crear una clase de configuracion, anotada con @Configuration o @Component
 * en la capa de Infraestructura donde tengamos todos los Bean que 
 * hay que crear cuando se levanta el contexto de Spring
 * 
 * */
// Se omite @Service
@RequiredArgsConstructor
public class TaskService implements CreateTaskUseCase, DeleteTaskUseCase, GetTaskUseCase, ListTaskUseCase, UpdateTaskUseCase, UploadTaskImageUseCase {

	private final TaskRepositoryPort taskRepositoryPort;
	private final FileStoragePort fileStoragePort;
	
	@Override
	public Task create(Task task) {
		return taskRepositoryPort.save(task);
	}

	@Override
	public Task getById(long id) {
		return taskRepositoryPort.findById(id).orElseThrow(() -> new TaskNotFoundException(id));
	}

  @Override
  public List<Task> findAll() {
    return taskRepositoryPort.findAll();
  }

	@Override
	public void deleteById(long id) {
		Task task = taskRepositoryPort.findById(id).orElseThrow(() -> new TaskNotFoundException(id));
		taskRepositoryPort.deleteById(id);
		fileStoragePort.delete(task.imagePath());
	}

	@Override
	public Task update(long id, Task task) {
		Task foundedTask = taskRepositoryPort.findById(id).orElseThrow(() -> new TaskNotFoundException(id));
		foundedTask.update(task.title(), task.description());
		foundedTask.changeStatusTo(task.status());
		return taskRepositoryPort.save(foundedTask);
	}

	@Override
	public Task uploadImage(long id, String fileName, byte[] content) {
		Task task = taskRepositoryPort.findById(id).orElseThrow(() -> new TaskNotFoundException(id));
		String previousImage = task.imagePath();
		String imagePath = fileStoragePort.store(fileName, content);
		task.attachImage(imagePath);
		Task saved = taskRepositoryPort.save(task.attachImage(imagePath));
		fileStoragePort.delete(previousImage);
		return saved;
	}

}
