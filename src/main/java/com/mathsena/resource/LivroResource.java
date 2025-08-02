package com.mathsena.resource;

import com.mathsena.model.Livro;
import com.mathsena.model.LivroDTO;
import com.mathsena.service.LivroService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/livros")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class LivroResource {

  @Inject LivroService livroService;

  @GET
  public Response listarTodos() {
    return Response.ok(livroService.listarTodos()).build();
  }

  @GET
  @Path("/{id}")
  public Response encontrarPorId(@PathParam("id") Long id) {
    return Response.ok(livroService.encontrarPorId(id)).build();
  }

  @POST
  public Response criarLivro(LivroDTO dto) {
    Livro novoLivro = livroService.criarLivro(dto);
    return Response.status(Response.Status.CREATED).entity(novoLivro).build();
  }

  @PUT
  @Path("/{id}")
  public Response atualizarLivro(@PathParam("id") Long id, LivroDTO dto) {
    Livro livroAtualizado = livroService.atualizarLivro(id, dto);
    return Response.ok(livroAtualizado).build();
  }

  @DELETE
  @Path("/{id}")
  public Response deletarLivro(@PathParam("id") Long id) {
    livroService.deletarLivro(id);
    return Response.noContent().build();
  }

  @GET
  @Path("/ranking")
  public Response getRanking() {
    return Response.ok(livroService.getRanking()).build();
  }
}
