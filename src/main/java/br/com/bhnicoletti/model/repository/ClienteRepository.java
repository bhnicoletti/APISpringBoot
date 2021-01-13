package br.com.bhnicoletti.model.repository;

import br.com.bhnicoletti.model.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
}
