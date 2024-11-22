package br.com.fiap.beans;

public class Pagamento {
	private String numero;
	private String nome;
	private String validade;
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getValidade() {
		return validade;
	}
	public void setValidade(String validade) {
		this.validade = validade;
	}
	public Pagamento(String numero, String nome, String validade) {
		super();
		this.numero = numero;
		this.nome = nome;
		this.validade = validade;
	}
	public Pagamento() {
		super();
	}

	
}
