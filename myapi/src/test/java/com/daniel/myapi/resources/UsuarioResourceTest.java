package com.daniel.myapi.resources;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.daniel.myapi.domain.Usuario;
import com.daniel.myapi.services.UsuarioService;

public class UsuarioResourceTest {

    private UsuarioResource usuarioResource;
    private UsuarioService usuarioService;

    @Before
    public void setUp() {
        usuarioService = mock(UsuarioService.class);
        usuarioResource = new UsuarioResource();
        usuarioResource.setServices(usuarioService);
    }

    @Test
    public void testFindById() {
        Usuario usuario = new Usuario();
        usuario.setId(1);
        usuario.setNome("Daniel");

        when(usuarioService.findById(1)).thenReturn(usuario);

        ResponseEntity<Usuario> response = usuarioResource.findById(1);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(usuario, response.getBody());
    }

    @Test
    public void testFindAll() {
        List<Usuario> usuarios = new ArrayList<>();
        Usuario usuario1 = new Usuario();
        usuario1.setId(1);
        usuario1.setNome("Daniel");
        Usuario usuario2 = new Usuario();
        usuario2.setId(2);
        usuario2.setNome("João");
        usuarios.add(usuario1);
        usuarios.add(usuario2);

        when(usuarioService.findAll()).thenReturn(usuarios);

        ResponseEntity<List<Usuario>> response = usuarioResource.findAll();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(usuarios, response.getBody());
    }

    @Test
    public void testUpdate() {
        Usuario usuario = new Usuario();
        usuario.setId(1);
        usuario.setNome("Daniel");

        when(usuarioService.update(1, usuario)).thenReturn(usuario);

        ResponseEntity<Usuario> response = usuarioResource.update(1, usuario);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(usuario, response.getBody());
    }

    @Test
    public void testCreate() {
        Usuario usuario = new Usuario();
        usuario.setId(1);
        usuario.setNome("Daniel");
        usuario.setLogin("daniel");
        usuario.setSenha("12348");

        when(usuarioService.create(usuario)).thenReturn(usuario);

        ResponseEntity<Usuario> response = usuarioResource.create(usuario);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @Test
    public void testDelete() {
        ResponseEntity<Void> response = usuarioResource.delete(1);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }
}