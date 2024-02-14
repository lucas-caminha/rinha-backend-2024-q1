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

	public void executeTransaction(Cliente cliente, TransacaoRequestDTO transacao) {
		
		if(TipoTransacaoEnum.DEBITO.getKey().equals(transacao.getTipo())) {
			executeTransactionDebit(cliente, transacao);
		}
		
		if(TipoTransacaoEnum.CREDITO.getKey().equals(transacao.getTipo())) {
			
		}
		
	}

	private void executeTransactionDebit(Cliente cliente, TransacaoRequestDTO transacao) {
		
		Integer valor = transacao.getValor();
		Integer limite = cliente.getLimite();
		Integer saldo = cliente.getSaldoInicial();
		
		if(atingiuLimite(valor, limite, saldo)) 
			throw new LimiteUltrapassadoException(LIMITE_ULTRAPASSADO);
		
		Integer novoSaldo = debit(saldo, valor);
		
		cliente.setSaldoInicial(novoSaldo);
		
		clienteService.save(cliente);
	}
	
	private Integer debit(Integer saldo, Integer valor) {
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
