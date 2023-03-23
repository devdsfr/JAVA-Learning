package com.daniel.myapi;

import com.daniel.myapi.domain.Usuario;
import com.daniel.myapi.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.Arrays;

@SpringBootApplication
@EnableJpaRepositories("com.example.cadastroveiculo.repository.marcaRepository")
@ComponentScan(basePackages = {"com.example.cadastroveiculo.service.veiculoService"})
public class MyapiApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(MyapiApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

//		Usuario u1 = new Usuario(null, "Daniel Ramos", "daniel", "123");
//		Usuario u2 = new Usuario(null, "Daniela RamOS Souza", "daniela", "123");
//
//		usuarioRepository.saveAll(Arrays.asList(u1,u2));

	}
}
