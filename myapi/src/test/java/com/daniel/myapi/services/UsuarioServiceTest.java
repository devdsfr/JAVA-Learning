package com.daniel.myapi.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.hibernate.ObjectNotFoundException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;

import com.daniel.myapi.domain.Usuario;
import com.daniel.myapi.repositories.UsuarioRepository;
import com.daniel.myapi.services.UsuarioService;

@RunWith(MockitoJUnitRunner.class)
public class UsuarioServiceTest {

    @Mock
    private UsuarioRepository repository;

    @InjectMocks
    private UsuarioService service;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testFindById() {
        Usuario usuario = new Usuario();
        usuario.setId(1);
        usuario.setNome("Daniel");
        usuario.setLogin("daniel");
        usuario.setSenha("123456");

        when(repository.findById(1)).thenReturn(Optional.of(usuario));

        Usuario result = service.findById(1);

        assertNotNull(result);
        assertEquals(usuario.getId(), result.getId());
        assertEquals(usuario.getNome(), result.getNome());
        assertEquals(usuario.getLogin(), result.getLogin());
        assertEquals(usuario.getSenha(), result.getSenha());
    }

    @Test(expected = ObjectNotFoundException.class)
    public void testFindByIdNotFound() {
        when(repository.findById(1)).thenReturn(Optional.empty());

        service.findById(1);
    }

    @Test
    public void testFindAll() {
        List<Usuario> usuarios = new ArrayList<>();
        Usuario usuario1 = new Usuario();
        usuario1.setId(1);
        usuario1.setNome("Daniel");
        usuario1.setLogin("daniel");
        usuario1.setSenha("123456");
        usuarios.add(usuario1);

        Usuario usuario2 = new Usuario();
        usuario2.setId(2);
        usuario2.setNome("João");
        usuario2.setLogin("joao");
        usuario2.setSenha("654321");
        usuarios.add(usuario2);

        when(repository.findAll()).thenReturn(usuarios);

        List<Usuario> result = service.findAll();

        assertNotNull(result);
        assertEquals(2, result.size());
        assertTrue(result.contains(usuario1));
        assertTrue(result.contains(usuario2));
    }

    @Test
    public void testUpdate() {
        Usuario usuario = new Usuario();
        usuario.setId(1);
        usuario.setNome("Daniel");
        usuario.setLogin("daniel");
        usuario.setSenha("123456");

        when(repository.findById(1)).thenReturn(Optional.of(usuario));
        when(repository.save(usuario)).thenReturn(usuario);

        Usuario usuarioAtualizado = new Usuario();
        usuarioAtualizado.setId(1);
        usuarioAtualizado.setNome("Daniel Silva");
        usuarioAtualizado.setLogin("danielsilva");
        usuarioAtualizado.setSenha("654321");

        Usuario result = service.update(1, usuarioAtualizado);

        assertNotNull(result);
        assertEquals(usuarioAtualizado.getId(), result.getId());
        assertEquals(usuarioAtualizado.getNome(), result.getNome());
        assertEquals(usuarioAtualizado.getLogin(), result.getLogin());
        assertEquals(usuarioAtualizado.getSenha(), result.getSenha());
    }

    @Test(expected = ObjectNotFoundException.class)
    public void testUpdateNotFound() {
        Usuario usuarioAtualizado = new Usuario();
        usuarioAtualizado.setId(1);
        usuarioAtualizado.setNome("Daniel Silva");
        usuarioAtualizado.setLogin("danielsilva");
        usuarioAtualizado.setSenha("654321");

        when(repository.findById(1)).thenReturn(Optional.empty());

        service.update(1, usuarioAtualizado);
    }

    @Test
    public void testCreate() {
        Usuario usuario = new Usuario();
        usuario.setId(1);
        usuario.setNome("Daniel");
        usuario.setLogin("daniel");
        usuario.setSenha("123456");

        when(repository.save(usuario)).thenReturn(usuario);

        Usuario result = service.create(usuario);

        assertNotNull(result);
        assertEquals(usuario.getId(), result.getId());
        assertEquals(usuario.getNome(), result.getNome());
        assertEquals(usuario.getLogin(), result.getLogin());
        assertEquals(usuario.getSenha(), result.getSenha());
    }

    @Test
    public void testDelete() {
        Usuario usuario = new Usuario();
        usuario.setId(1);
        usuario.setNome("Daniel");
        usuario.setLogin("daniel");
        usuario.setSenha("123456");

        when(repository.findById(1)).thenReturn(Optional.of(usuario));

        service.delete(1);
    }

    @Test(expected = ObjectNotFoundException.class)
    public void testDeleteNotFound() {
        when(repository.findById(1)).thenReturn(Optional.empty());

        service.delete(1);
    }
}