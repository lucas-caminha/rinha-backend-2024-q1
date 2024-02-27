package br.com.pagamento.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.pagamento.dto.request.TransacaoRequestDTO;
import br.com.pagamento.entity.cliente.Cliente;
import br.com.pagamento.entity.tipotransacao.TipoTransacaoEnum;
import br.com.pagamento.exception.LimiteUltrapassadoException;

@Service
public class TransacaoService {
	
	private final static String LIMITE_ULTRAPASSADO = "Limite foi ultrapassado.";
	
	@Autowired
	private ClienteService clienteService;

	public Cliente executeTransaction(Cliente cliente, TransacaoRequestDTO transacao) {	
		if(TipoTransacaoEnum.DEBITO.getKey().equals(transacao.getTipo())) {
			executeTransactionDebit(cliente, transacao);
		} else if(TipoTransacaoEnum.CREDITO.getKey().equals(transacao.getTipo())) {
			executeTransactionCredit(cliente, transacao);
		}
		
		return cliente;
	}

	private void executeTransactionCredit(Cliente cliente, TransacaoRequestDTO transacao) {
		// TODO Auto-generated method stub
		
	}

	public Cliente executeTransactionDebit(Cliente cliente, TransacaoRequestDTO transacao) {		
		if(atingiuLimite(transacao.getValor(), cliente.getLimite(), cliente.getSaldoInicial())) 
			throw new LimiteUltrapassadoException(LIMITE_ULTRAPASSADO);
		
		Integer novoSaldo = debite(cliente.getSaldoInicial(), transacao.getValor());		
		cliente.setSaldoInicial(novoSaldo);		
		
		return clienteService.save(cliente);
	}
	
	private Integer debite(Integer saldo, Integer valor) {
		saldo = (saldo - valor);
		return saldo;
	}

	/** 
	 * 	Uma transação de débito nunca pode deixar o saldo do cliente menor que seu limite disponível.
	 * 	Por exemplo, um cliente com limite de 1000 (R$ 10) nunca deverá ter o saldo menor que -1000 (R$ -10).
	 *  Nesse caso, um saldo de -1001 ou menor significa inconsistência.
	 **/
	private boolean atingiuLimite(Integer valor, Integer limite, Integer saldo) {		
		saldo = (saldo - valor);
		if(saldo < ~limite) {
			return true;
		}
		return false;
	}

}
