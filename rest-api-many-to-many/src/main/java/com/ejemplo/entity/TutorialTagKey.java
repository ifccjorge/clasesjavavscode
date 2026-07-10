package com.ejemplo.entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class TutorialTagKey implements Serializable {

  @Column(name = "tutorial_id")
  Long tutorialId;

  @Column(name = "tag_id")
  Long tagId;
}
