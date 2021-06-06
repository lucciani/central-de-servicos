package io.github.lucciani.cs.domain.repository;

import java.util.List;

import io.github.lucciani.cs.domain.model.Ticket;

public interface TicketRepositoryQueries {

	List<Ticket> find(String texto);
	
	List<Ticket> buscaTicketsDoDiaPorPalavraChave(String texto);

}