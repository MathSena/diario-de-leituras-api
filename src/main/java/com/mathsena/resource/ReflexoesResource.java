package com.mathsena.resource;

import com.mathsena.model.Reflexao;
import com.mathsena.service.ReflexoesService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/livros/{livroId}/reflexoes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ReflexoesResource {

  @Inject ReflexoesService reflexoesService;

  @GET
  public Response listarReflexoes(@PathParam("livroId") Long livroId) {
    return Response.ok(reflexoesService.listarPorLivroId(livroId)).build();
  }

  @POST
  public Response adicionarReflexao(@PathParam("livroId") Long livroId, Reflexao reflexao) {
    Reflexao novaReflexao = reflexoesService.adicionarReflexao(livroId, reflexao);
    return Response.status(Response.Status.CREATED).entity(novaReflexao).build();
  }
}
