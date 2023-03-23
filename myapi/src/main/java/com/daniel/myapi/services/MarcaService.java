package com.daniel.myapi.services;

import com.daniel.myapi.repositories.MarcaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MarcaService {

    @Autowired
    private MarcaRepository marcaRepository;
}
