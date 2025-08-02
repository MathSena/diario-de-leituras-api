package com.mathsena.service;

import com.mathsena.model.Livro;
import com.mathsena.model.Reflexao;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;
import java.util.List;

@ApplicationScoped
public class ReflexoesService {

  public List<Reflexao> listarPorLivroId(Long livroId) {
    Livro livro =
        (Livro)
            Livro.findByIdOptional(livroId)
                .orElseThrow(
                    () -> new NotFoundException("Livro com ID " + livroId + " não encontrado."));
    return livro.reflexoes;
  }

  @Transactional
  public Reflexao adicionarReflexao(Long livroId, Reflexao reflexao) {
    Livro livro =
        (Livro)
            Livro.findByIdOptional(livroId)
                .orElseThrow(
                    () -> new NotFoundException("Livro com ID " + livroId + " não encontrado."));

    reflexao.livro = livro;
    reflexao.persist();
    return reflexao;
  }
}
