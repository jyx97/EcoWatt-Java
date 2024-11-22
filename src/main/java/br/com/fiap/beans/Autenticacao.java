package br.com.fiap.beans;

public class Autenticacao {
    private String email;
    private String senha;

    public Autenticacao(String email, String senha) {
        this.email = email;
        this.senha = senha;
    }

    public boolean validarCredenciais() {
        // Simulação: validação básica
        return email.equals("teste@dominio.com") && senha.equals("123456");
    }
}
