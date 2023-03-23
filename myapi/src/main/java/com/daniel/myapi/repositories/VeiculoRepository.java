package com.daniel.myapi.repositories;

import com.daniel.myapi.domain.Marca;
import com.daniel.myapi.domain.Veiculo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VeiculoRepository extends JpaRepository<Veiculo, String> {
    Optional<Veiculo> findByPlaca(String placa);
    Page<Veiculo> findByMarca(Marca marca, Pageable pageable);
    boolean existsByPlaca(String placa);
}
