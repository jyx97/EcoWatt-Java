package br.com.fiap.beans;

public class Usuario {
	private String cpf;
	private String nome;
	private Contato contato;
	private Pagamento pagamento;
	private Plano plano; 
	
	
	public Plano getPlano() {
		return plano;
	}
	public void setPlano(Plano plano) {
		this.plano = plano;
	}
	public Pagamento getPagamento() {
		return pagamento;
	}
	public void setPagamento(Pagamento pagamento) {
		this.pagamento = pagamento;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Contato getContato() {
		return contato;
	}
	public void setContato(Contato contato) {
		this.contato = contato;
	}
	public Usuario(String cpf, String nome, Contato contato, Pagamento pagamento, Plano plano) {
		super();
		this.cpf = cpf;
		this.nome = nome;
		this.contato = contato;
		this.pagamento = pagamento;
		this.plano = plano;
	}
	public Usuario() {
		super();
	}

	
	
}
	