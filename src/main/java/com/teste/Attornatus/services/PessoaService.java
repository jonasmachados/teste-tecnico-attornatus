package com.teste.Attornatus.services;

import com.teste.Attornatus.entities.Pessoa;
import com.teste.Attornatus.repositories.PessoaRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.teste.Attornatus.services.exceptions.ResourceNotFoundException;
import javax.persistence.EntityNotFoundException;

@Service
public class PessoaService {

    @Autowired
    private PessoaRepository repository;

    public List<Pessoa> findAll() {
        return repository.findAll();
    }

    public Pessoa findById(Long id) {
        Optional<Pessoa> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ResourceNotFoundException(id));
    }

    public Pessoa insert(Pessoa obj) {
        return repository.save(obj);
    }

    public Pessoa update(Long id, Pessoa obj) {
        try {
            Pessoa entity = findById(id);;
            updateData(entity, obj);
            return repository.save(entity);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException(id);
        }

    }

    private void updateData(Pessoa entity, Pessoa obj) {
        entity.setNome(obj.getNome());
        entity.setDataNascimento(obj.getDataNascimento());

    }

}
