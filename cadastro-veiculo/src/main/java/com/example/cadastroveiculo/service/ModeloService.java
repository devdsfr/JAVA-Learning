package com.example.cadastroveiculo.service;

import com.example.cadastroveiculo.repository.ModeloRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class ModeloService {

    @Autowired
    private ModeloRepository modeloRepository;
}
