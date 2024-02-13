package br.com.pagamento.dto.response;

import java.io.Serializable;

public class TransacaoResponseDTO implements Serializable {

	private static final long serialVersionUID = 5832674498456653021L;
	
	private Double limite;
	private Double saldo;
	
	public Double getLimite() {
		return limite;
	}
	public void setLimite(Double limite) {
		this.limite = limite;
	}
	public Double getSaldo() {
		return saldo;
	}
	public void setSaldo(Double saldo) {
		this.saldo = saldo;
	}
	
}
