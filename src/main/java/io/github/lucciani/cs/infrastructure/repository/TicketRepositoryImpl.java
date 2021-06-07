package io.github.lucciani.cs.infrastructure.repository;

import static io.github.lucciani.cs.infrastructure.repository.specification.TicketSpecifications.porDescricao;
import static io.github.lucciani.cs.infrastructure.repository.specification.TicketSpecifications.porTitulo;
import static io.github.lucciani.cs.infrastructure.repository.specification.TicketSpecifications.ticketsDoDia;
import static io.github.lucciani.cs.infrastructure.util.UtilitarioData.getInicioDia;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import io.github.lucciani.cs.domain.model.Ticket;
import io.github.lucciani.cs.domain.repository.TicketRepository;
import io.github.lucciani.cs.domain.repository.TicketRepositoryQueries;

@Repository
public class TicketRepositoryImpl implements TicketRepositoryQueries {

	@PersistenceContext
	private EntityManager manager;
	
	@Lazy
	@Autowired
	private TicketRepository ticketRepository;

	@Override
	public List<Ticket> find(String texto) {

		CriteriaBuilder builder = manager.getCriteriaBuilder();

		CriteriaQuery<Ticket> criteria = builder.createQuery(Ticket.class);
		Root<Ticket> root = criteria.from(Ticket.class);

		List<Predicate> predicates = new ArrayList<Predicate>();
		Predicate disjunction = builder.disjunction();

		if (StringUtils.hasLength(texto)) {
			Predicate tituloPredicate = builder.like(root.get("titulo"), "%" + texto + "%");
			Predicate descricaoPredicate = builder.like(root.get("descricao"), "%" + texto + "%");
			
			disjunction = builder.or(tituloPredicate,descricaoPredicate);
			predicates.add(disjunction);
		}

		Predicate dataAberturaPredicate = builder
				.greaterThanOrEqualTo(root.get("dataAbertura"), 				
				getInicioDia(LocalDateTime.now(ZoneId.systemDefault())));
		
		predicates.add(dataAberturaPredicate);

		criteria.where(predicates.toArray(new Predicate[0]));

		TypedQuery<Ticket> query = manager.createQuery(criteria);

		return query.getResultList();
	}

	@Override
	public List<Ticket> buscaTicketsDoDiaPorPalavraChave(String texto) {
		return ticketRepository
				.findAll(porTitulo(texto).or(porDescricao(texto)).and(ticketsDoDia()));
	}

}
