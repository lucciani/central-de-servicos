package io.github.lucciani.cs.api.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.github.lucciani.cs.domain.exception.EntidadeEmUsoException;
import io.github.lucciani.cs.domain.exception.EntidadeNaoEncontradaException;
import io.github.lucciani.cs.domain.model.Ticket;
import io.github.lucciani.cs.domain.repository.TicketRepository;
import io.github.lucciani.cs.domain.service.CadastroTicketService;

@RestController
@RequestMapping(value = "/tickets")
public class TicketController {
	
	@Autowired
	private TicketRepository ticketRepository;
	
	@Autowired
	private CadastroTicketService cadastroTicket;
	
	@GetMapping
	public List<Ticket> listar(){
		return ticketRepository.findAll();
	}
	
	@GetMapping(value = "/{ticketId}")
	public ResponseEntity<Ticket> buscar(@PathVariable Long ticketId){
		Optional<Ticket> ticket = ticketRepository.findById(ticketId);
		
		if (ticket.isPresent()) {
			return ResponseEntity.ok(ticket.get());
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@GetMapping(value = "/por-status")
	public List<Ticket> buscarPorStatus(String descricao){
		return ticketRepository.findByStatusDescricao(descricao);
	}
	
	@GetMapping(value = "/por-categoria")
	public List<Ticket> buscarPorCategoria(String descricao){
		return ticketRepository.findByCategoriaNome(descricao);
	}
	
	@GetMapping(value = "/busca-tickets")
	public List<Ticket> buscarTicketDoDiaPorPalavraChave(String texto){
		return ticketRepository.find(texto);
	}
	
	@PostMapping
	@ResponseStatus(value = HttpStatus.CREATED)
	public Ticket adicionar(@RequestBody Ticket ticket) {
		return cadastroTicket.salva(ticket);
	}
	
	@PutMapping(value = "/{ticketId}")
	public ResponseEntity<?> atualizar(@PathVariable Long ticketId,
			@RequestBody Ticket ticket){
		
		try {
			Optional<Ticket> ticketAtual = ticketRepository.findById(ticketId);
			
			String[] ignoreProperties = new String[] {"id", "dataAbertura"};
			
			if (ticketAtual.isEmpty()) {
				return ResponseEntity.notFound().build();
			}
			
			BeanUtils.copyProperties(ticket, ticketAtual.get(), ignoreProperties);
			Ticket ticketSalvo = cadastroTicket.salva(ticketAtual.get());
			
			return ResponseEntity.ok(ticketSalvo);
			
		} catch (EntidadeNaoEncontradaException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@DeleteMapping(value = "/{ticketId}")
	public ResponseEntity<?> remover(@PathVariable Long ticketId){
		try {
			cadastroTicket.remover(ticketId);
			return ResponseEntity.noContent().build();
		} catch (EntidadeEmUsoException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).build();

		} catch (EntidadeNaoEncontradaException e) {
			return ResponseEntity.notFound().build();
		}
	}

}
