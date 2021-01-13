package br.com.bhnicoletti.model.repository;

import br.com.bhnicoletti.model.entity.Servico;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServicoRepository extends JpaRepository<Servico, Integer> {
}
