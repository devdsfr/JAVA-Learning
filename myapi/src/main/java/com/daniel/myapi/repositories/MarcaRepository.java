package com.daniel.myapi.repositories;

import com.daniel.myapi.domain.Marca;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MarcaRepository extends JpaRepository<Marca, String> {
    Optional<Marca> findByNomeIgnoreCase(String nome);
}

