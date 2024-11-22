package br.com.fiap.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import br.com.fiap.beans.ContaEnergia;
import br.com.fiap.conexoes.ConexaoFactory;

public class ContaEnergiaDao {

    private Connection minhaConexao;

    public ContaEnergiaDao() throws ClassNotFoundException, SQLException {
        this.minhaConexao = new ConexaoFactory().conexao();
    }

    // Verifica se o CPF existe na tabela REGISTRO
    public boolean isCpfValido(String cpf) throws SQLException {
        String sql = "SELECT COUNT(*) FROM REGISTRO WHERE cpf = ?";
        try (PreparedStatement stmt = minhaConexao.prepareStatement(sql)) {
            stmt.setString(1, cpf);
            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next() && rs.getInt(1) > 0;
            }
        }
    }

    // Insere uma nova conta no banco, vinculando ao CPF do usuário logado
    public void inserir(ContaEnergia conta) throws SQLException {
        // Verifica se o CPF existe na tabela REGISTRO
        if (!isCpfValido(conta.getCpfUsuario())) {
            throw new IllegalArgumentException("CPF não encontrado. Não é possível inserir a conta.");
        }

        // Query para inserção com a coluna CPF
        String sql = "INSERT INTO CONTA_LUZ (cpf, valor, consumo_kwh, data) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = minhaConexao.prepareStatement(sql)) {
            stmt.setString(1, conta.getCpfUsuario());  // CPF do usuário logado
            stmt.setDouble(2, conta.getValorConta());
            stmt.setDouble(3, conta.getConsumoKwh());
            stmt.setTimestamp(4, Timestamp.valueOf(conta.getData()));  // Conversão de LocalDateTime para java.sql.Timestamp
            stmt.executeUpdate();
        }
    }

    // Verifica se uma conta do mês já foi enviada
    public boolean isContaDoMesJaEnviada(String cpf, int mes, int ano) throws SQLException {
        String sql = "SELECT COUNT(*) FROM CONTA_LUZ WHERE cpf = ? AND EXTRACT(MONTH FROM data) = ? AND EXTRACT(YEAR FROM data) = ?";
        try (PreparedStatement stmt = minhaConexao.prepareStatement(sql)) {
            stmt.setString(1, cpf);
            stmt.setInt(2, mes);
            stmt.setInt(3, ano);
            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next() && rs.getInt(1) > 0;
            }
        }
    }


    // Conta total de registros para um CPF
    public int obterTotalContasEnviadas(String cpfUsuario) throws SQLException {
        String sql = "SELECT COUNT(*) FROM CONTA_LUZ WHERE cpf = ?";
        try (PreparedStatement stmt = minhaConexao.prepareStatement(sql)) {
            stmt.setString(1, cpfUsuario);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }
        }
        return 0;
    }
}
