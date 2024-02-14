package br.com.pagamento.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.pagamento.entity.cliente.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

}
