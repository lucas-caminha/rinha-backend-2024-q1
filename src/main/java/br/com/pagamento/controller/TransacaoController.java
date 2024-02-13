package br.com.pagamento.controller;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.pagamento.dto.request.TransacaoRequestDTO;
import br.com.pagamento.dto.response.TransacaoResponseDTO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;

@RestController
@Validated
public class TransacaoController {
	
	@RequestMapping(value =  "/clientes/{id}/transacoes", method = RequestMethod.POST)
	TransacaoResponseDTO executaTransacao(@Min(1) @PathVariable String id, @Valid @RequestBody TransacaoRequestDTO transacao) {
		
		return new TransacaoResponseDTO();
	}
	

}
