package br.com.pagamento.exception;

public class LimiteUltrapassadoException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	
	public LimiteUltrapassadoException(String msg) {
		super(msg);
	}

}
