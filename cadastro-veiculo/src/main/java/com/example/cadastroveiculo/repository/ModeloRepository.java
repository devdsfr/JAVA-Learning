package com.example.cadastroveiculo.repository;

import com.example.cadastroveiculo.model.Modelo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ModeloRepository extends JpaRepository<Modelo, String> {
}
