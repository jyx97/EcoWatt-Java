package br.com.fiap.bo;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.logging.Level;
import java.util.logging.Logger;

import br.com.fiap.beans.ContaEnergia;
import br.com.fiap.dao.ContaEnergiaDao;

public class ContaEnergiaBO {

    private static final Logger logger = Logger.getLogger(ContaEnergiaBO.class.getName());
    private final ContaEnergiaDao contaEnergiaDAO;

    public ContaEnergiaBO(ContaEnergiaDao contaEnergiaDAO) {
        this.contaEnergiaDAO = contaEnergiaDAO;
    }

    public void salvarConta(ContaEnergia conta) {
        try {
            String cpfUsuario = conta.getCpfUsuario();

            // Verifica se o CPF é válido
            if (!contaEnergiaDAO.isCpfValido(cpfUsuario)) {
                throw new RuntimeException("CPF não encontrado. Verifique o cadastro do usuário.");
            }

            // Verifica o número de contas já enviadas
            int totalContas = contaEnergiaDAO.obterTotalContasEnviadas(cpfUsuario);

            if (totalContas < 5) {
                // Caso o usuário não tenha enviado as 5 contas iniciais
                throw new RuntimeException("Usuário precisa enviar mais " + (5 - totalContas) + " contas para iniciar o mapeamento.");
            }

            // Verifica se já foi enviada a conta do mês atual
            LocalDateTime dataConta = conta.getData();
            if (contaEnergiaDAO.isContaDoMesJaEnviada(cpfUsuario, dataConta.getMonthValue(), dataConta.getYear())) {
                throw new RuntimeException("A conta do mês já foi enviada.");
            }

            // Insere a nova conta
            contaEnergiaDAO.inserir(conta);
            logger.log(Level.INFO, "Conta salva com sucesso para CPF: {0}", cpfUsuario);

        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Erro ao salvar conta: {0}", e.getMessage());
            throw new RuntimeException("Erro ao salvar conta: " + e.getMessage(), e);
        }
    }

    public String verificarEstadoUsuario(String cpfUsuario) {
        try {
            // Verifica se o CPF é válido
            if (!contaEnergiaDAO.isCpfValido(cpfUsuario)) {
                return "CPF não encontrado. Realize o cadastro.";
            }

            // Verifica o número de contas já enviadas
            int totalContas = contaEnergiaDAO.obterTotalContasEnviadas(cpfUsuario);

            if (totalContas < 5) {
                return "Usuário precisa enviar mais " + (5 - totalContas) + " contas para iniciar o mapeamento.";
            } else {
                return "Usuário já enviou as contas iniciais. Pode enviar a conta do mês.";
            }

        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Erro ao verificar estado do usuário: {0}", e.getMessage());
            return "Erro ao verificar estado do usuário: " + e.getMessage();
        }
    }
}
