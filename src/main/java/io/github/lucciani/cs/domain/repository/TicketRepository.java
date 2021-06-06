package io.github.lucciani.cs.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.github.lucciani.cs.domain.model.Ticket;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {
	
	List<Ticket> findByStatusDescricao(String descricao);
	
	List<Ticket> findByCategoriaNome(String nome);
	
	List<Ticket> find(String texto);

}
