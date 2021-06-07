package io.github.lucciani.cs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import io.github.lucciani.cs.infrastructure.repository.CustomJpaRepositoryImpl;

@SpringBootApplication
@EnableJpaRepositories(repositoryBaseClass = CustomJpaRepositoryImpl.class)
public class CentralDeServicosApplication {

	public static void main(String[] args) {
		SpringApplication.run(CentralDeServicosApplication.class, args);
	}

}
