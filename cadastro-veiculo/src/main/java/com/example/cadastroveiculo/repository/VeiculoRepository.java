package com.example.cadastroveiculo.repository;

import com.example.cadastroveiculo.model.Veiculo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VeiculoRepository extends JpaRepository<Veiculo, String> {
    Optional<Veiculo> findByPlaca(String placa);
    List<Veiculo> findByMarca(String marca, Pageable pageable);
    boolean existsByPlaca(String placa);
}
