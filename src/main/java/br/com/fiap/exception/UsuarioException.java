package br.com.fiap.exception;

public class UsuarioException extends Excecao {

    private static final int CODIGO_ERRO = 1001;

    public UsuarioException(String mensagem) {
        super(CODIGO_ERRO, mensagem);
    }

    public UsuarioException(String mensagem, String detalhes) {
        super(CODIGO_ERRO, mensagem, detalhes);
    }

    public UsuarioException(String mensagem, Throwable causa) {
        super(CODIGO_ERRO, mensagem + ": " + causa.getMessage(), causa.getMessage());
    }
}
