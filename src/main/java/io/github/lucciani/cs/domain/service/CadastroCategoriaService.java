package io.github.lucciani.cs.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import io.github.lucciani.cs.domain.exception.EntidadeEmUsoException;
import io.github.lucciani.cs.domain.exception.EntidadeNaoEncontradaException;
import io.github.lucciani.cs.domain.model.Categoria;
import io.github.lucciani.cs.domain.repository.CategoriaRepository;

@Service
public class CadastroCategoriaService {

	@Autowired
	private CategoriaRepository categoriaRepository;

	public Categoria salvar(Categoria categoria) {
		return categoriaRepository.save(categoria);
	}

	public void remover(Long categoriaId) {
		try {
			categoriaRepository.deleteById(categoriaId);

		} catch (EmptyResultDataAccessException e) {
			throw new EntidadeNaoEncontradaException(
					String.format("Não existe um cadastro de categirua com o código %d", categoriaId));
		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(
					String.format("Categoria de código %d não pode ser removido, pois está em uso.", categoriaId));
		}
	}

}
