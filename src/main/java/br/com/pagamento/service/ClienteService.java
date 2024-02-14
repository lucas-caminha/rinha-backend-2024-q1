package br.com.pagamento.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.pagamento.entity.cliente.Cliente;
import br.com.pagamento.repository.ClienteRepository;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository repository;

	public Optional<Cliente> findClienteById(Integer id) {
		return repository.findById(id);
	}

	public void save(Cliente cliente) {
		repository.save(cliente);
	}
	
}
