package com.mathsena.resource;

import com.mathsena.model.Livro;
import org.jboss.logging.Logger;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

@Path("/livros")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class LivroResource {

  private static final Logger LOG = Logger.getLogger(LivroResource.class);


  @GET
  public List<Livro> listarTodos() {
    LOG.info("Listando todos os livros");
    return Livro.listAll();
  }


  @POST
  @Transactional
  public Response criarLivro(Livro livro) {
    LOG.info("Criando novo livro: " + livro);
    if (livro == null || livro.titulo == null || livro.autor == null) {
      return Response.status(Response.Status.BAD_REQUEST)
          .entity("Livro, título e autor são obrigatórios.")
          .build();
    }

    livro.persist();
    return Response.status(Response.Status.CREATED).entity(livro).build();
  }


  @PUT
  @Path("/{id}")
  @Transactional
  public Response atualizarLivro(@PathParam("id") Long id, Livro livroAtualizado) {
    LOG.info("Atualizando livro com ID: " + id);
    Livro livroExistente = Livro.findById(id);
    if (livroExistente == null) {
      return Response.status(Response.Status.NOT_FOUND).entity("Livro não encontrado.").build();
    }

    if (livroAtualizado.titulo != null) {
      livroExistente.titulo = livroAtualizado.titulo;
    }
    if (livroAtualizado.autor != null) {
      livroExistente.autor = livroAtualizado.autor;
    }
    if (livroAtualizado.categoria != null) {
      livroExistente.categoria = livroAtualizado.categoria;
    }
    if (livroAtualizado.status != null) {
      livroExistente.status = livroAtualizado.status;
    }
    if (livroAtualizado.dataInicio != null) {
      livroExistente.dataInicio = livroAtualizado.dataInicio;
    }
    if (livroAtualizado.dataConclusao != null) {
      livroExistente.dataConclusao = livroAtualizado.dataConclusao;
    }
    if (livroAtualizado.reflexoes != null) {
      livroExistente.reflexoes = livroAtualizado.reflexoes;
    }
    if (livroAtualizado.nota != null) {
      livroExistente.nota = livroAtualizado.nota;
    }

    livroExistente.persist();

    return Response.ok(livroExistente).build();
  }

  @DELETE
  @Path("/{id}")
  @Transactional
  public Response deletarLivro(@PathParam("id") Long id) {
    LOG.info("Deletando livro com ID: " + id);
    Livro livro = Livro.findById(id);
    if (livro == null) {
      return Response.status(Response.Status.NOT_FOUND).entity("Livro não encontrado.").build();
    }

    livro.delete();
    return Response.noContent().build();
  }
}
