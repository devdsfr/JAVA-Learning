package com.example.cadastroveiculo.repository;

import com.example.cadastroveiculo.model.Marca;
import com.example.cadastroveiculo.model.Veiculo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MarcaRepository extends JpaRepository<Marca, String> {
}

