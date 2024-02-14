package br.com.pagamento.entity.cliente;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Cliente {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private Integer limite;
	private Integer saldoInicial;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getLimite() {
		return limite;
	}
	public void setLimite(Integer limite) {
		this.limite = limite;
	}
	public Integer getSaldoInicial() {
		return saldoInicial;
	}
	public void setSaldoInicial(Integer saldoInicial) {
		this.saldoInicial = saldoInicial;
	}
	
}
