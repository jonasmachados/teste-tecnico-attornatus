package com.teste.Attornatus.config;

import com.teste.Attornatus.entities.Endereco;
import com.teste.Attornatus.entities.Pessoa;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import com.teste.Attornatus.repositories.PessoaRepository;
import com.teste.Attornatus.repositories.EnderecoRepository;

@Configuration
public class TestConfig implements CommandLineRunner {

    SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy");

    @Autowired
    private PessoaRepository pessoaRepositories;

    @Autowired
    private EnderecoRepository enderecoRepositories;

    @Override
    public void run(String... args) throws Exception {

        Pessoa p1 = new Pessoa(null, "Jose Luiz", formatador.parse("14/10/1980"));
        Pessoa p2 = new Pessoa(null, "Ana Maria", formatador.parse("18/05/1992"));

        Endereco e1 = new Endereco(null, "Avenida do Estado", "01025‑020", "563", "SP");
        Endereco e2 = new Endereco(null, "Avenida Exterior", "01015‑100", "98", "SP");
        enderecoRepositories.saveAll(Arrays.asList(e1, e2));

        p1.getEnderecos().add(e1);
        p2.getEnderecos().add(e2);
        
        pessoaRepositories.saveAll(Arrays.asList(p1, p2));
        
    }

}
