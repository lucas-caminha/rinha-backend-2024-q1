package br.com.pagamento.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.pagamento.dto.request.TransacaoRequestDTO;
import br.com.pagamento.dto.response.TransacaoResponseDTO;

@RestController
public class TransacaoController {
	
	@RequestMapping(value =  "/clientes/{id}/transacoes")
	public TransacaoResponseDTO executaTransacao(@PathVariable String id, @RequestBody TransacaoRequestDTO transacao) {
		
		return new TransacaoResponseDTO();
	}
	

}
