package br.com.fiap.beans;

public class Recompensa {
	 private String cpfUsuario;
	 private int xpAtual;
	public String getCpfUsuario() {
		return cpfUsuario;
	}
	public void setCpfUsuario(String cpfUsuario) {
		this.cpfUsuario = cpfUsuario;
	}
	public int getXpAtual() {
		return xpAtual;
	}
	public void setXpAtual(int xpAtual) {
		this.xpAtual = xpAtual;
	}
	public Recompensa(String cpfUsuario, int xpAtual) {
		super();
		this.cpfUsuario = cpfUsuario;
		this.xpAtual = xpAtual;
	}
	public Recompensa() {
		super();
	}
	 
	 
}
