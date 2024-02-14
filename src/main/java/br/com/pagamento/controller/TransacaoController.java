package br.com.pagamento.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.pagamento.dto.request.TransacaoRequestDTO;
import br.com.pagamento.entity.cliente.Cliente;
import br.com.pagamento.exception.InvalidParamException;
import br.com.pagamento.service.ClienteService;
import br.com.pagamento.service.TransacaoService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;

@RestController
@Validated
public class TransacaoController {
	
	private static final String ID_INVALIDO = "ID informado não é um inteiro.";
	private static final String TIPO_TRANSACAO_INCORRETA = "Tipo da transação informada é inválida.";
	
	@Autowired
	private TransacaoService service;
	
	@Autowired
	private ClienteService clienteService;
	
	@RequestMapping(value =  "/clientes/{id}/transacoes", method = RequestMethod.POST)
	ResponseEntity<Object> executaTransacao(@Min(1) @PathVariable Integer id, @Valid @RequestBody TransacaoRequestDTO transacao) {
		
		if(!isInteger(id))
			throw new InvalidParamException(ID_INVALIDO);
		
		if(!isTipoTransacaoCorreta(transacao))
			throw new InvalidParamException(TIPO_TRANSACAO_INCORRETA);
		
		Optional<Cliente> cliente = clienteService.findClienteById(id);
		
		if(cliente.isEmpty()) 
			return ResponseEntity.notFound().build();
		
		service.executeTransaction(cliente.get(), transacao);
		
		return ResponseEntity.ok().build();
	}

	private boolean isTipoTransacaoCorreta(TransacaoRequestDTO transacao) {
		if(transacao.getTipo().equals("C") || transacao.getTipo().equals("D")) {
			return true;
		}
		return false;
	}

	private boolean isInteger(@Min(1) Integer id) {
		return id != null && id > 0;
	}
	

}
