package com.ejemplo.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Access;
import jakarta.persistence.AccessType;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "usuarios")
@Access(AccessType.FIELD)
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Usuario implements Serializable {
  private static final long serialVersionUID = 1L;
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;
  private String username;
  private String password;
  private LocalDate dateOfBirth;
  private String status;

  @Builder.Default
  @ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
  @JoinTable(
    name = "usuarios_contactos", 
      joinColumns = {
        @JoinColumn(
          name = "usuario_id",
          nullable = false,
          foreignKey = @ForeignKey(name = "fk_usuario_1")
        )
      },
      inverseJoinColumns = {
        @JoinColumn(
          name = "contacto_id",
          nullable = false,
          foreignKey = @ForeignKey(name = "fk_contacto_1")
        )
      }
  )
  @EqualsAndHashCode.Exclude
  private Set<Contacto> contactos = new HashSet<>();
  
  public void addContacto(Contacto contacto) {
    this.contactos.add(contacto);
    contacto.getUsuarios().add(this);
  }

  public void removeContacto(long contactoId) {
    var tag = this.contactos.stream().filter(t -> t.getUserId() == contactoId).findFirst().orElse(null);
    if (tag != null) {
      this.contactos.remove(tag);
      tag.getUsuarios().remove(this);
    }
  }
}
