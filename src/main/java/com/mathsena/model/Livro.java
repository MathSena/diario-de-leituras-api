package com.mathsena.model;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "livro")
public class Livro extends PanacheEntityBase {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  public Long id;

  public String titulo;
  public String autor;

  @Enumerated(EnumType.STRING)
  public Categoria categoria;

  @Enumerated(EnumType.STRING)
  public Status status;

  public LocalDate dataInicio;
  public LocalDate dataConclusao;

  public Integer nota;

  @OneToMany(
      mappedBy = "livro",
      cascade = CascadeType.ALL,
      orphanRemoval = true,
      fetch = FetchType.EAGER)
  public List<Reflexao> reflexoes = new ArrayList<>();

  public String capaUrl;
}
