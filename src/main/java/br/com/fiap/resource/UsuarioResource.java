package br.com.fiap.resource;

import br.com.fiap.beans.Usuario;
import br.com.fiap.dao.UsuarioDao;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/usuarios") 
public class UsuarioResource {

    private UsuarioDao usuarioDao;

    public UsuarioResource() {
        try {
            this.usuarioDao = new UsuarioDao(); // Inicializa o DAO
        } catch (Exception e) {
            e.printStackTrace(); // Gerencia a falha na inicialização do DAO
        }
    }
    @GET
    public Response getUsuario(){
    	return Response.status(Response.Status.OK).entity("Usuário ok").build();
    }

    // Endpoint para adicionar um novo usuário
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response adicionarUsuario(Usuario usuario) {
        if (usuario.getCpf() == null || usuario.getCpf().isEmpty()) {
            return Response.status(Response.Status.BAD_REQUEST).entity("CPF do usuário é obrigatório!").build();
        }

        try {
            // Insere o usuário no banco via DAO
            String mensagem = usuarioDao.inserir(usuario);
            if (mensagem.contains("Erro")) {
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(mensagem).build();
            }
            return Response.status(Response.Status.CREATED).entity(usuario).build(); // Retorna o usuário criado
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erro ao adicionar usuário: " + e.getMessage()).build();
        }
    }

    // Endpoint para atualizar os dados de um usuário
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response atualizarUsuario(Usuario usuario) {
        if (usuario.getCpf() == null || usuario.getCpf().isEmpty()) {
            return Response.status(Response.Status.BAD_REQUEST).entity("CPF do usuário é obrigatório!").build();
        }

        try {
            // Atualiza os dados do usuário no banco via DAO
            String mensagem = usuarioDao.atualizar(usuario);
            if (mensagem.contains("Erro")) {
                return Response.status(Response.Status.BAD_REQUEST).entity(mensagem).build();
            }
            return Response.ok(usuario).build(); // Retorna o usuário atualizado
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erro ao atualizar usuário: " + e.getMessage()).build();
        }
    }

    // Endpoint para excluir um usuário
    @DELETE
    @Path("/{cpf}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response excluirUsuario(@PathParam("cpf") String cpf) {
        if (cpf == null || cpf.isEmpty()) {
            return Response.status(Response.Status.BAD_REQUEST).entity("CPF é obrigatório!").build();
        }

        try {
            // Exclui o usuário no banco via DAO
            String mensagem = usuarioDao.excluir(cpf);
            if (mensagem.contains("Erro")) {
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(mensagem).build();
            }
            return Response.status(Response.Status.NO_CONTENT).build(); // Retorna 204 após exclusão
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erro ao excluir usuário: " + e.getMessage()).build();
        }
    }
}
