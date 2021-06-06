package io.github.lucciani.cs.infrastructure.repository.specification;

import java.time.LocalDateTime;
import java.time.ZoneId;

import org.springframework.data.jpa.domain.Specification;

import io.github.lucciani.cs.domain.model.Ticket;
import io.github.lucciani.cs.infrastructure.util.UtilitarioData;

public class TicketSpecifications {

	public static Specification<Ticket> ticketsDoDia() {
		return (root, query, builder) -> 
				builder.greaterThanOrEqualTo(root.get("dataAbertura"),
						UtilitarioData.getInicioDia(LocalDateTime.now(ZoneId.systemDefault())));
	}

	public static Specification<Ticket> porTitulo(String titulo) {
		return (root, query, builder) -> 
			builder.like(root.get("titulo"), "%" + titulo + "%");
	}

	public static Specification<Ticket> porDescricao(String descricao) {
		return (root, query, builder) -> 
			builder.like(root.get("descricao"), "%" + descricao + "%");
	}

}
