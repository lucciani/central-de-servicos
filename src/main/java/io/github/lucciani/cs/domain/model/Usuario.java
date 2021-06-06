package io.github.lucciani.cs.domain.model;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Usuario {
	
	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private String usuario;
	
//	@Column(nullable = false)
//	private String senha;
	
	@Column(nullable = false)
	private String nome;
	
	@Column(nullable = false)
	private String email;
	
//	@Column(name = "data_cadastro", nullable = false)
//	private LocalDateTime dataCadastro;
//	
//	@Column(name = "data_atualizacao", nullable = false)
//	private LocalDateTime dataAtualizacao;
//	
//	@PrePersist
//	public void prePersist() {
//		this.dataCadastro = getDataFormatada(LocalDateTime.now(ZoneId.systemDefault()));
//		this.dataAtualizacao = getDataFormatada(LocalDateTime.now(ZoneId.systemDefault()));
//	}
//	
//	@PreUpdate
//	public void preUpdate() {
//		this.dataCadastro = getDataCadastro();
//		this.dataAtualizacao = getDataFormatada(LocalDateTime.now(ZoneId.systemDefault()));
//	}
//	
//	private LocalDateTime getDataFormatada(LocalDateTime data) {
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
//        String dataFormatada = formatter.format(data);
//        return LocalDateTime.parse(dataFormatada, formatter);
//	}

}
