package br.com.fiap.beans;

public class Plano {
		private String cpf;
	    private String tipoPlano; // "padrao", "premium", "premiumPlus"

	    public Plano(String cpf, String tipoPlano) {
	        this.cpf = cpf;
	        this.tipoPlano = tipoPlano;
	    }

	    // Getters e setters
	    public String getTipoPlano() { return tipoPlano; }
	    public void setTipoPlano(String tipoPlano) { this.tipoPlano = tipoPlano; }

	    // Método para atualizar o plano
	    public void atualizarPlano(String novoPlano) {
	        this.tipoPlano = novoPlano;
	    }
	}


