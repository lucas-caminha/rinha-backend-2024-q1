package br.com.pagamento.dto.request;

import java.io.Serializable;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class TransacaoRequestDTO implements Serializable {

	private static final long serialVersionUID = -4632549327929283689L;

	@NotNull(message = "Informe um valor")
	@Min(1)
	private Integer valor;
	@NotBlank(message = "Informe um tipo de transação")
	private String tipo;
    @NotBlank(message = "Informe uma descrição")
	private String descricao;
	
	public Integer getValor() {
		return valor;
	}
	public void setValor(Integer valor) {
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
