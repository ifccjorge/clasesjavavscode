package com.ejemplo.entity;

import java.io.Serializable;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tutorials_tags")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class TutorialTag implements Serializable {

  @EmbeddedId
  private TutorialTagKey id;

  @ManyToOne
  @MapsId("tutorialId")
  @JoinColumn(name = "tutorial_id")
  private Tutorial tutorial;

  @ManyToOne
  @MapsId("tagId")
  @JoinColumn(name = "tag_id")
  private Tag tag;

  private int rating;
}
