package br.com.pagamento.service;

import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.pagamento.dto.request.TransacaoRequestDTO;
import br.com.pagamento.entity.cliente.Cliente;
import br.com.pagamento.exception.LimiteUltrapassadoException;
import br.com.pagamento.repository.ClienteRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TransacaoServiceTest {

	@Mock
	ClienteRepository clienteRepository;
	
	@Autowired
	TransacaoService transacaoService;
	
	@MockBean
	private ClienteService clienteService;
		
	@Test(expected = LimiteUltrapassadoException.class)
	public void quandoExecutarTransacaoDebitoEValorDaTransacaoForMaiorQueLimitedeveLancarLimiteUltrapassadoException() {
				
        Cliente cliente = new Cliente();
        cliente.setId(1);
        cliente.setLimite(100000);
        cliente.setSaldoInicial(0);

        TransacaoRequestDTO transacao = new TransacaoRequestDTO();
        transacao.setTipo("D");
        transacao.setValor(100001);
        transacao.setDescricao("Teste");
        
        when(clienteService.save(cliente)).thenReturn(cliente);
	    	
		transacaoService.executeTransactionDebit(cliente, transacao);
	}
	
}
