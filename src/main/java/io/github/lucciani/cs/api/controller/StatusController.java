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
import io.github.lucciani.cs.domain.model.Status;
import io.github.lucciani.cs.domain.repository.StatusRepository;
import io.github.lucciani.cs.domain.service.CadastroStatusService;

@RestController
@RequestMapping(value = "/status")
public class StatusController {
	
	@Autowired
	private StatusRepository statusRepository;
	
	@Autowired
	private CadastroStatusService cadastroStatus;
	
	@GetMapping
	public List<Status> listar(){
		return statusRepository.findAll();
	}
	
	@GetMapping(value = "/{statusId}")
	public ResponseEntity<Status> buscar(@PathVariable Long statusId){
		Optional<Status> status = statusRepository.findById(statusId);
		
		if (status.isPresent()) {
			return ResponseEntity.ok(status.get());
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping
	@ResponseStatus(value = HttpStatus.CREATED)
	public Status adicionar(@RequestBody Status status) {
		return cadastroStatus.salvar(status);
	}
	
	@PutMapping(value = "/{statusId}")
	public ResponseEntity<?> atualizar(@PathVariable Long statusId,
			@RequestBody Status status){
		
		Optional<Status> statusAtual = statusRepository.findById(statusId);
		
		if (statusAtual.isPresent()) {
			BeanUtils.copyProperties(status, statusAtual.get(), "id");
			Status statusSalvo = cadastroStatus.salvar(statusAtual.get());
			return ResponseEntity.ok(statusSalvo);
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping(value = "/{statusId}")
	public ResponseEntity<?> remover(@PathVariable Long statusId){
		try {
			cadastroStatus.remover(statusId);
			return ResponseEntity.noContent().build();
		} catch (EntidadeEmUsoException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());

		} catch (EntidadeNaoEncontradaException e) {
			return ResponseEntity.notFound().build();
		}
	}

}
