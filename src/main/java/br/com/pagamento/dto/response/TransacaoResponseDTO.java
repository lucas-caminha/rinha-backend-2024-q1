package br.com.pagamento.dto.response;

import java.io.Serializable;

public class TransacaoResponseDTO implements Serializable {

	private static final long serialVersionUID = 5832674498456653021L;
	
	private Integer limite;
	private Integer saldo;
		
	public TransacaoResponseDTO(Integer limite, Integer saldo) {
		this.limite = limite;
		this.saldo = saldo;
	}
	
	public TransacaoResponseDTO() {}
	
	
	public Integer getLimite() {
		return limite;
	}
	public void setLimite(Integer limite) {
		this.limite = limite;
	}
	public Integer getSaldo() {
		return saldo;
	}
	public void setSaldo(Integer saldo) {
		this.saldo = saldo;
	}
	
}
