package com.example.ssxyz_ltda;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.ssxyz_ltda.models.CategoriaCurso;
import com.example.ssxyz_ltda.repository.CategoriaCursoRepository;

@SpringBootApplication
public class SsxyzLtdaApplication {

	@Bean
	public CommandLineRunner init(
		@Autowired CategoriaCursoRepository catCursoRepository) {
			return args -> {
				catCursoRepository.save(new CategoriaCurso(null, "Cat1"));
			};
		}

	public static void main(String[] args) {
		SpringApplication.run(SsxyzLtdaApplication.class, args);		
	}

}
