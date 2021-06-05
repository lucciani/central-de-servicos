package io.github.lucciani.cs.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import io.github.lucciani.cs.domain.exception.EntidadeEmUsoException;
import io.github.lucciani.cs.domain.exception.EntidadeNaoEncontradaException;
import io.github.lucciani.cs.domain.model.Prioridade;
import io.github.lucciani.cs.domain.repository.PrioridadeRepository;

@Service
public class CadastroPrioridadeService {

	@Autowired
	private PrioridadeRepository prioridadeRepository;

	public Prioridade salvar(Prioridade prioridade) {
		return prioridadeRepository.save(prioridade);
	}

	public void remover(Long prioridadeId) {
		try {
			prioridadeRepository.deleteById(prioridadeId);
		} catch (EmptyResultDataAccessException e) {
			throw new EntidadeNaoEncontradaException(
					String.format("Não existe um cadastro de prioridade com o código %d", prioridadeId));
		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(
					String.format("Prioridade de código %d não pode ser removido, pois está em uso.", prioridadeId));
		}
	}

}
