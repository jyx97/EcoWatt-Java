package br.com.fiap.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import br.com.fiap.beans.Usuario;
import br.com.fiap.conexoes.ConexaoFactory;

public class UsuarioDao {

    private Connection minhaConexao;

    public UsuarioDao() throws ClassNotFoundException, SQLException {
        this.minhaConexao = new ConexaoFactory().conexao();
    }

    public String inserir(Usuario usuario) throws SQLException {
        String sql = "INSERT INTO REGISTRO (cpf, nome, email, cep, telefone, endereco, plano) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = minhaConexao.prepareStatement(sql)) {
            stmt.setString(1, usuario.getCpf());
            stmt.setString(2, usuario.getNome());
            stmt.setString(3, usuario.getContato().getEmail());
            stmt.setString(4, usuario.getContato().getendereco().getCep());
            stmt.setString(5, usuario.getContato().getTelefone());
            stmt.setString(6, usuario.getContato().getendereco().formatarEndereco());
            stmt.setString(7, usuario.getPlano().getTipoPlano());

            int linhasAfetadas = stmt.executeUpdate();
            return (linhasAfetadas > 0) ? "Usuário inserido com sucesso" : "Erro ao inserir usuário";
        }
    }

    public String atualizar(Usuario usuario) throws SQLException {
        String sql = "UPDATE REGISTRO SET nome = ?, email = ?, cep = ?, telefone = ?, endereco = ?, plano = ? WHERE cpf = ?";

        try (PreparedStatement stmt = minhaConexao.prepareStatement(sql)) {
            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getContato().getEmail());
            stmt.setString(3, usuario.getContato().getendereco().getCep());
            stmt.setString(4, usuario.getContato().getTelefone());
            stmt.setString(5, usuario.getContato().getendereco().formatarEndereco());
            stmt.setString(6, usuario.getPlano().getTipoPlano());
            stmt.setString(7, usuario.getCpf());

            int linhasAfetadas = stmt.executeUpdate();
            return (linhasAfetadas > 0) ? "Usuário atualizado com sucesso" : "Erro ao atualizar usuário";
        }
    }

    public String excluir(String cpf) throws SQLException {
        String sql = "DELETE FROM REGISTRO WHERE cpf = ?";

        try (PreparedStatement stmt = minhaConexao.prepareStatement(sql)) {
            stmt.setString(1, cpf);

            int linhasAfetadas = stmt.executeUpdate();
            return (linhasAfetadas > 0) ? "Usuário excluído com sucesso" : "Erro ao excluir usuário";
        }
    }
}
