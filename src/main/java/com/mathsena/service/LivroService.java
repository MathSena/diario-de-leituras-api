package com.mathsena.service;

import com.mathsena.model.Livro;
import com.mathsena.model.LivroDTO;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;
import java.util.List;

@ApplicationScoped
public class LivroService {

  public List<Livro> listarTodos() {
    return Livro.listAll();
  }

  public Livro encontrarPorId(Long id) {
    return (Livro)
        Livro.findByIdOptional(id)
            .orElseThrow(() -> new NotFoundException("Livro com ID " + id + " não encontrado."));
  }

  @Transactional
  public Livro criarLivro(LivroDTO dto) {
    if (dto.titulo == null
        || dto.titulo.trim().isEmpty()
        || dto.autor == null
        || dto.autor.trim().isEmpty()) {
      throw new IllegalArgumentException("Título e Autor são obrigatórios.");
    }

    Livro livro = new Livro();
    copiarDtoParaEntidade(dto, livro);
    livro.persist();
    return livro;
  }

  @Transactional
  public Livro atualizarLivro(Long id, LivroDTO dto) {
    Livro livroExistente = encontrarPorId(id);
    copiarDtoParaEntidade(dto, livroExistente);
    return livroExistente;
  }

  @Transactional
  public void deletarLivro(Long id) {
    Livro livro = encontrarPorId(id);
    livro.delete();
  }

  public List<Livro> getRanking() {
    return Livro.list("status = 'LIDO' ORDER BY nota DESC");
  }

  private void copiarDtoParaEntidade(LivroDTO dto, Livro entidade) {
    entidade.titulo = dto.titulo;
    entidade.autor = dto.autor;
    entidade.categoria = dto.categoria;
    entidade.status = dto.status;
    entidade.dataInicio = dto.dataInicio;
    entidade.dataConclusao = dto.dataConclusao;
    entidade.nota = dto.nota;
    entidade.capaUrl = dto.capaUrl;
  }
}
