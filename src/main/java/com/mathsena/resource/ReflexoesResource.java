package com.mathsena.resource;

import com.mathsena.model.Livro;
import com.mathsena.model.Reflexao;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

@Path("/livros/{livroId}/reflexoes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ReflexoesResource {

  @GET
  public List<Reflexao> listarReflexoes(@PathParam("livroId") Long livroId) {
    Livro livro = Livro.findById(livroId);
    if (livro == null) {
      throw new NotFoundException("Livro não encontrado com o ID: " + livroId);
    }

    return livro.reflexoes;
  }

  @POST
  @Transactional
  public Response adicionarReflexao(@PathParam("livroId") Long livroId, Reflexao reflexao) {
    Livro livro = Livro.findById(livroId);
    if (livro == null) {
      throw new NotFoundException("Livro não encontrado");
    }
    reflexao.livro = livro; // Associa a reflexão ao livro
    reflexao.persist(); // Salva a reflexão no banco

    return Response.status(Response.Status.CREATED).entity(reflexao).build();
  }
}
