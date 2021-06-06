package io.github.lucciani.cs.domain.model;

import java.time.LocalDateTime;
import java.time.ZoneId;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import io.github.lucciani.cs.infrastructure.util.UtilitarioData;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Ticket {

	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String titulo;

	@Column(nullable = false)
	private String descricao;

	@ManyToOne
	@JoinColumn(name = "status_id", nullable = false)
	private Status status;

	@ManyToOne
	@JoinColumn(name = "prioridade_id", nullable = false)
	private Prioridade prioridade;

	@ManyToOne
	@JoinColumn(name = "categoria_id", nullable = false)
	private Categoria categoria;

	@Column(name = "data_abertura", nullable = false)
	private LocalDateTime dataAbertura;

	@Column(name = "data_atualizacao", nullable = false)
	private LocalDateTime dataAtualizacao;

	@PrePersist
	public void prePersist() {
		this.dataAbertura = UtilitarioData.getDataFormatada(LocalDateTime.now());
		this.dataAtualizacao = UtilitarioData.getDataFormatada(LocalDateTime.now(ZoneId.systemDefault()));
	}

	@PreUpdate
	public void preUpdate() {
		this.dataAbertura = getDataAbertura();
		this.dataAtualizacao = UtilitarioData.getDataFormatada(LocalDateTime.now(ZoneId.systemDefault()));
	}

}
