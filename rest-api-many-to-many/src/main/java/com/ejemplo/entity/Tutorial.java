package com.ejemplo.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "tutorials")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@ToString(exclude = { "tags", "ratings" })
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Tutorial implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @Column(name = "title")
  @JsonProperty("title")
  private String titulo;

  @Column(name = "description")
  @JsonProperty("description")
  private String descripcion;

  @Column(name = "published")
  @JsonProperty("published")
  private boolean publicado;

  @Builder.Default
  @ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
  @JoinTable(
    name = "tutorials_tags", 
      joinColumns = {
        @JoinColumn(
          name = "tutorial_id",
          nullable = false,
          foreignKey = @ForeignKey(name = "fk_tutorial_1")
        )
      },
      inverseJoinColumns = {
        @JoinColumn(
          name = "tag_id",
          nullable = false,
          foreignKey = @ForeignKey(name = "fk_tag_1")
        )
      }
  )
  private Set<Tag> tags = new HashSet<>();

  @Builder.Default
  @OneToMany(mappedBy = "tutorial")
  @JsonIgnore
  private Set<TutorialTag> ratings = new HashSet<>();

  @CreationTimestamp
  @Column(name = "created_at", nullable = false, updatable = false)
  private LocalDateTime createdAt;

  @UpdateTimestamp
  @Column(name = "updated_at")
  private LocalDateTime updatedAt;

  public void addTag(Tag tag) {
    this.tags.add(tag);
    tag.getTutorials().add(this);
  }

  public void removeTag(long tagId) {
    var tag = this.tags.stream().filter(t -> t.getId() == tagId).findFirst().orElse(null);
    if (tag != null) {
      this.tags.remove(tag);
      tag.getTutorials().remove(this);
    }
  }
}
