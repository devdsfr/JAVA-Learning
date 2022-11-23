package com.daniel.myapi.resources;

import com.daniel.myapi.domain.Usuario;
import com.daniel.myapi.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/usuarios")
public class UsuarioResource {

    @Autowired
    private UsuarioService services;

    @GetMapping(value = "/{id}")
    public ResponseEntity<Usuario> findById(@PathVariable Integer id){
        Usuario obj = this.services.findById(id);
        return ResponseEntity.ok().body(obj);

    }
}
