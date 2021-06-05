package io.github.lucciani.cs.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import io.github.lucciani.cs.domain.exception.EntidadeEmUsoException;
import io.github.lucciani.cs.domain.exception.EntidadeNaoEncontradaException;
import io.github.lucciani.cs.domain.model.Status;
import io.github.lucciani.cs.domain.repository.StatusRepository;

@Service
public class CadastroStatusService {
	
	@Autowired
	private StatusRepository statusRepository;
	
	public Status salvar(Status status) {
		return statusRepository.save(status);
	}

	public void remover(Long statusId) {
		try {
			statusRepository.deleteById(statusId);
		} catch (EmptyResultDataAccessException e) {
			throw new EntidadeNaoEncontradaException(
					String.format("Não existe um cadastro de status com o código %d", statusId));
		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(
					String.format("Status de código %d não pode ser removido, pois está em uso.", statusId));
		}
		
	}

}
