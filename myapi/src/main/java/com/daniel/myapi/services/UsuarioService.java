package com.daniel.myapi.services;

import com.daniel.myapi.domain.Usuario;
import com.daniel.myapi.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository repository;

    public Usuario findById(Integer id){
        Optional<Usuario> obj = repository.findById(id);
        return obj.orElse(null);
    }
}
