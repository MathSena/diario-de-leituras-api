package com.mathsena.model;

import java.time.LocalDate;

public class LivroDTO {
  public String titulo;
  public String autor;
  public Categoria categoria;
  public Status status;
  public LocalDate dataInicio;
  public LocalDate dataConclusao;
  public Integer nota;
  public String capaUrl;
}
