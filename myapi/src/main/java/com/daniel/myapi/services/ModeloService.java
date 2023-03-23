package com.daniel.myapi.services;

import com.daniel.myapi.repositories.ModeloRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ModeloService {

    @Autowired
    private ModeloRepository modeloRepository;
}
