package br.com.pagamento.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.pagamento.entity.cliente.Cliente;
import br.com.pagamento.repository.ClienteRepository;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository repository;

	public Cliente findClienteById(Integer id) {
		return repository.findById(id).get();
	}

	public Cliente save(Cliente cliente) {
		return repository.save(cliente);
	}
	
}
