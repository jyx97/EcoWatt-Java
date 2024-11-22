package br.com.fiap.beans;

import br.com.fiap.model.Endereco;

public class Contato {
	private String email;
	private String telefone;
	private Endereco endereco; 
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public Endereco getendereco() {
		return endereco;
	}
	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
	public Contato(String email, String telefone) {
		super();
		this.email = email;
		this.telefone = telefone;
	}
	public Contato() {
		super();
	}
	

}
