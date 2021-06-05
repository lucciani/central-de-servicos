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
import io.github.lucciani.cs.domain.model.Categoria;
import io.github.lucciani.cs.domain.repository.CategoriaRepository;
import io.github.lucciani.cs.domain.service.CadastroCategoriaService;

@RestController
@RequestMapping(value = "/categorias")
public class CategoriaController {

	@Autowired
	private CategoriaRepository categoriaRepository;

	@Autowired
	private CadastroCategoriaService cadastroCategoria;

	@GetMapping
	public List<Categoria> listar() {
		return categoriaRepository.findAll();
	}

	@GetMapping(value = "/{categoriaId}")
	public ResponseEntity<Categoria> buscar(@PathVariable Long categoriaId) {
		Optional<Categoria> categoria = categoriaRepository.findById(categoriaId);

		if (categoria.isPresent()) {
			return ResponseEntity.ok(categoria.get());
		}

		return ResponseEntity.notFound().build();
	}

	@PostMapping
	@ResponseStatus(value = HttpStatus.CREATED)
	public Categoria adicionar(@RequestBody Categoria categoria) {
		return cadastroCategoria.salvar(categoria);
	}

	@PutMapping(value = "/{categoriaId}")
	public ResponseEntity<?> atualizar(@PathVariable Long categoriaId, @RequestBody Categoria categoria) {
		Optional<Categoria> categoriaAtual = categoriaRepository.findById(categoriaId);

		if (categoriaAtual.isPresent()) {
			BeanUtils.copyProperties(categoria, categoriaAtual.get(), "id");
			Categoria categoriaSalva = cadastroCategoria.salvar(categoriaAtual.get());
			return ResponseEntity.ok(categoriaSalva);
		}

		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping(value = "/{categoriaId}")
	public ResponseEntity<?> remover(@PathVariable Long categoriaId){
		try {
			cadastroCategoria.remover(categoriaId);
			return ResponseEntity.noContent().build();
		} catch (EntidadeEmUsoException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());

		} catch (EntidadeNaoEncontradaException e) {
			return ResponseEntity.notFound().build();
		}
	}

}
