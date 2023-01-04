package com.teste.Attornatus.services;

import com.teste.Attornatus.entities.Endereco;
import com.teste.Attornatus.repositories.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EnderecoService {

    @Autowired
    private EnderecoRepository repository;

    public Endereco insert(Endereco obj) {
        return repository.save(obj);
    }
}
