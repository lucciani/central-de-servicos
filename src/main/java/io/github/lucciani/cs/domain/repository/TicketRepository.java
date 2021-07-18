package io.github.lucciani.cs.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import io.github.lucciani.cs.domain.model.Ticket;

@Repository
public interface TicketRepository 
	extends CustomJpaRepository<Ticket, Long>, 
		TicketRepositoryQueries,
		JpaSpecificationExecutor<Ticket>{
	
	@Query("FROM Ticket t LEFT JOIN FETCH t.prioridade LEFT JOIN FETCH t.categoria LEFT JOIN FETCH t.status")
	List<Ticket> findAll();
	
	List<Ticket> findByStatusDescricao(String descricao);
	
	List<Ticket> findByCategoriaNome(String nome);
	

}
