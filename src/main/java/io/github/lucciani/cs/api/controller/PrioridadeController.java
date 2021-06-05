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
import io.github.lucciani.cs.domain.model.Prioridade;
import io.github.lucciani.cs.domain.repository.PrioridadeRepository;
import io.github.lucciani.cs.domain.service.CadastroPrioridadeService;

@RestController
@RequestMapping(value = "/prioridades")
public class PrioridadeController {

	@Autowired
	private PrioridadeRepository prioridadeRepository;

	@Autowired
	private CadastroPrioridadeService cadastroPrioridade;

	@GetMapping
	private List<Prioridade> listar() {
		return prioridadeRepository.findAll();
	}

	@GetMapping(value = "/{prioridadeId}")
	public ResponseEntity<Prioridade> buscar(@PathVariable Long prioridadeId) {
		Optional<Prioridade> prioridade = prioridadeRepository.findById(prioridadeId);

		if (prioridade.isPresent()) {
			return ResponseEntity.ok(prioridade.get());
		}

		return ResponseEntity.notFound().build();
	}

	@PostMapping
	@ResponseStatus(value = HttpStatus.CREATED)
	public Prioridade adicionar(@RequestBody Prioridade prioridade) {
		return cadastroPrioridade.salvar(prioridade);
	}

	@PutMapping(value = "/{prioridadeId}")
	public ResponseEntity<?> atualizar(@PathVariable Long prioridadeId, @RequestBody Prioridade prioridade) {

		Optional<Prioridade> prioridadeAtual = prioridadeRepository.findById(prioridadeId);

		if (prioridadeAtual.isPresent()) {
			BeanUtils.copyProperties(prioridade, prioridadeAtual.get(), "id");
			Prioridade prioridadeSalva = cadastroPrioridade.salvar(prioridadeAtual.get());
			return ResponseEntity.ok(prioridadeSalva);
		}

		return ResponseEntity.notFound().build();
	}

	@DeleteMapping(value = "/{prioridadeId}")
	public ResponseEntity<?> remover(@PathVariable Long prioridadeId) {
		try {
			cadastroPrioridade.remover(prioridadeId);
			return ResponseEntity.noContent().build();
		} catch (EntidadeEmUsoException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());

		} catch (EntidadeNaoEncontradaException e) {
			return ResponseEntity.notFound().build();
		}
	}

}
