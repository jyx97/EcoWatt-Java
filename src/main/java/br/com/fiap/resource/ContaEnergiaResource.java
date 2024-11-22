package br.com.fiap.resource;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.fiap.beans.ContaEnergia;
import br.com.fiap.bo.ContaEnergiaBO;
import br.com.fiap.dao.ContaEnergiaDao;

@Path("/contas") // Base do endpoint será /resource/contas
public class ContaEnergiaResource {

    // Inicialize o DAO e o BO
    private ContaEnergiaDao contaEnergiaDao;
    private ContaEnergiaBO contaEnergiaBO;

    // Construtor que recebe o DAO
    public ContaEnergiaResource() {
        try {
            this.contaEnergiaDao = new ContaEnergiaDao();
            this.contaEnergiaBO = new ContaEnergiaBO(contaEnergiaDao);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Adicionar uma nova conta
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response adicionarConta(ContaEnergia conta) {
        try {
            contaEnergiaBO.salvarConta(conta); // Usa o método BO para salvar a conta
            return Response.status(Response.Status.CREATED).entity(conta).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erro ao adicionar conta: " + e.getMessage()).build();
        }
    }

    // Atualizar uma conta existente (ajuste caso necessário)
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response atualizarConta(ContaEnergia conta) {
        try {
            // Atualiza uma conta, mas você pode criar uma lógica específica aqui.
            contaEnergiaBO.salvarConta(conta); // Pode ser o mesmo método que o POST, ou criar uma lógica de atualização no BO
            return Response.ok(conta).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erro ao atualizar conta: " + e.getMessage()).build();
        }
    }

}
