package br.com.pagamento.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.pagamento.dto.request.TransacaoRequestDTO;
import br.com.pagamento.entity.cliente.Cliente;
import br.com.pagamento.service.ClienteService;
import br.com.pagamento.service.TransacaoService;

@WebMvcTest(TransacaoController.class)
class TransacaoControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private TransacaoService transacaoService;
	
	@MockBean
	private ClienteService clienteService;
	
	@Autowired 
	private ObjectMapper mapper;


	@Test
	void testing() throws Exception {
		
	   Cliente cliente = new Cliente();
	   cliente.setId(1);
	   cliente.setLimite(100000);
	   cliente.setSaldoInicial(0);

	   TransacaoRequestDTO transacao = new TransacaoRequestDTO();
	   transacao.setTipo("D");
	   transacao.setValor(1000);
	   transacao.setDescricao("Teste");
		
	   when(transacaoService.executeTransaction(cliente, transacao)).thenReturn(cliente);
	   
	   when(clienteService.findClienteById(1)).thenReturn(cliente);
	   
	   String json = mapper.writeValueAsString(transacao);
	   
	   this.mockMvc.perform(post("/clientes/1/transacoes")
			   .contentType(MediaType.APPLICATION_JSON)
			   .content(json)
			   .accept(MediaType.APPLICATION_JSON)
			   ).andDo(print())
	   		.andExpect(status().isOk());
				
	}
}