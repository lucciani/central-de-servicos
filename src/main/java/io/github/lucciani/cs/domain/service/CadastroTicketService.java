package io.github.lucciani.cs.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import io.github.lucciani.cs.domain.exception.EntidadeEmUsoException;
import io.github.lucciani.cs.domain.exception.EntidadeNaoEncontradaException;
import io.github.lucciani.cs.domain.model.Categoria;
import io.github.lucciani.cs.domain.model.Prioridade;
import io.github.lucciani.cs.domain.model.Status;
import io.github.lucciani.cs.domain.model.Ticket;
import io.github.lucciani.cs.domain.repository.CategoriaRepository;
import io.github.lucciani.cs.domain.repository.PrioridadeRepository;
import io.github.lucciani.cs.domain.repository.StatusRepository;
import io.github.lucciani.cs.domain.repository.TicketRepository;

@Service
public class CadastroTicketService {

	@Autowired
	private TicketRepository ticketRepository;
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private PrioridadeRepository prioridadeRepository;
	
	@Autowired
	private StatusRepository statusRepository;

	public Ticket salva(Ticket ticket) {
		
		Long categoriaId = ticket.getCategoria().getId();
		Long prioridadeId = ticket.getPrioridade().getId();
		Long statusId = ticket.getStatus().getId();

		Categoria categoria = categoriaRepository.findById(categoriaId)
			.orElseThrow(() -> new EntidadeNaoEncontradaException(
				String.format("Não existe cadastro de categoria com código %d", categoriaId)));
		
		Prioridade prioridade = prioridadeRepository.findById(prioridadeId).orElseThrow(() -> new EntidadeNaoEncontradaException(
				String.format("Não existe cadastro de prioridade com código %d", prioridadeId)));
		
		Status status = statusRepository.findById(statusId).orElseThrow(() -> new EntidadeNaoEncontradaException(
				String.format("Não existe cadastro de status com código %d", statusId)));
		
		ticket.setCategoria(categoria);
		ticket.setPrioridade(prioridade);
		ticket.setStatus(status);
		
		return ticketRepository.save(ticket);
	}

	public void remover(Long ticketId) {

		try {
			ticketRepository.deleteById(ticketId);
		} catch (EmptyResultDataAccessException e) {
			throw new EntidadeNaoEncontradaException(
					String.format("Não existe um cadastro de um ticket com o código %d", ticketId));
		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(
					String.format("Ticket de código %d não pode ser removido, pois está em uso.", ticketId));
		}
	}

}
