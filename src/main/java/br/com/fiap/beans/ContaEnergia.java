package br.com.fiap.beans;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class ContaEnergia {
	private String cpfUsuario;
    private double consumoKwh;
    private double valorConta;
    private LocalDateTime data;
	public String getCpfUsuario() {
		return cpfUsuario;
	}
	public void setCpfUsuario(String cpfUsuario) {
		this.cpfUsuario = cpfUsuario;
	}
	public double getConsumoKwh() {
		return consumoKwh;
	}
	public void setConsumoKwh(double consumoKwh) {
		this.consumoKwh = consumoKwh;
	}
	public double getValorConta() {
		return valorConta;
	}
	public void setValorConta(double valorConta) {
		this.valorConta = valorConta;
	}
	public LocalDateTime getData() {
		return data;
	}
	public void setData(LocalDateTime data) {
		this.data = data;
	}
	public ContaEnergia(String cpfUsuario, double consumoKwh, double valorConta, LocalDateTime localDate) {
		super();
		this.cpfUsuario = cpfUsuario;
		this.consumoKwh = consumoKwh;
		this.valorConta = valorConta;
		this.data = localDate;
	}
	public ContaEnergia() {
		super();
	}
    


}
