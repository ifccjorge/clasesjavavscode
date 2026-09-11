package com.ejemplo.domain.model;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Task {
  @EqualsAndHashCode.Include
  private long id;
  private String title;
  private String description;
  private TaskStatus status;
  private LocalDateTime createdAt;
  private LocalDateTime completedAt;


  public void complete() {

  }

  public void reopen() {

  }

  public void initDefaults() {

  }
}
