package br.com.fiap.exception;

public abstract class Excecao extends RuntimeException {
	    private int codigoErro;
	    private String detalhes;

	    public Excecao(int codigoErro, String mensagem) {
	        super(mensagem);
	        this.codigoErro = codigoErro;
	    }

	    public Excecao(int codigoErro, String mensagem, String detalhes) {
	        super(mensagem);
	        this.codigoErro = codigoErro;
	        this.detalhes = detalhes;
	    }

	    public int getCodigoErro() {
	        return codigoErro;
	    }

	    public String getDetalhes() {
	        return detalhes;
	    }

	    @Override
	    public String toString() {
	        return "Erro " + codigoErro + ": " + getMessage() + (detalhes != null ? " | Detalhes: " + detalhes : "");
	    }
}


