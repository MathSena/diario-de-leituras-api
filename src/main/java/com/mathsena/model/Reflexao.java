package com.mathsena.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import java.time.LocalDateTime;

@Entity
public class Reflexao extends PanacheEntity {

  public String conteudo;
  public LocalDateTime dataCriacao = LocalDateTime.now();

  @ManyToOne(fetch = FetchType.LAZY)
  @JsonIgnore // Ignorar loops infinitos de serialização JSON
  public Livro livro;
}
