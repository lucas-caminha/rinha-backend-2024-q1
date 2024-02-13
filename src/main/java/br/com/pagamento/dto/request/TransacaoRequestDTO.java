package br.com.pagamento.dto.request;

import java.io.Serializable;

public class TransacaoRequestDTO implements Serializable {

	private static final long serialVersionUID = -4632549327929283689L;

	private Double valor;
	private String tipo;
	private String descricao;
	
	public Double getValor() {
		return valor;
	}
	public void setValor(Double valor) {
		this.valor = valor;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
		
}
