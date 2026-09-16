package com.ejemplo.infrastructure.adapter.out.persistence;

import org.mapstruct.Mapper;

import com.ejemplo.domain.model.Task;

@Mapper(componentModel = "spring")
public interface TaskPersistenceMapper {
	Task toDomain(TaskJpaEntity taskJpaEntity);
	TaskJpaEntity toJpaEntity(Task task);
}
