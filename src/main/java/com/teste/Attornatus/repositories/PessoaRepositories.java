package com.teste.Attornatus.repositories;

import com.teste.Attornatus.entities.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PessoaRepositories extends JpaRepository<Pessoa, Long>{
    
}
