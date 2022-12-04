package br.com.dicasdeumdev.api.services;

import br.com.dicasdeumdev.api.domain.User;

import java.util.List;

public interface UserService {

    User findById(Long id);
    List<User> findAll();

}
