package com.teste.Attornatus.config;

import com.teste.Attornatus.entities.Endereco;
import com.teste.Attornatus.entities.Pessoa;
import com.teste.Attornatus.repositories.EnderecoRepositories;
import com.teste.Attornatus.repositories.PessoaRepositories;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TestConfig implements CommandLineRunner {

    SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy");

    @Autowired
    private PessoaRepositories pessoaRepositories;

    @Autowired
    private EnderecoRepositories enderecoRepositories;

    @Override
    public void run(String... args) throws Exception {

        Pessoa p1 = new Pessoa(null, "Jose Luiz", formatador.parse("14/10/1980"));
        Pessoa p2 = new Pessoa(null, "Ana Maria", formatador.parse("18/05/1992"));
        pessoaRepositories.saveAll(Arrays.asList(p1, p2));

        Endereco e1 = new Endereco(null, "Avenida do Estado", "01025‑020", "563");
        Endereco e2 = new Endereco(null, "Avenida Exterior", "01015‑100", "98");
        enderecoRepositories.saveAll(Arrays.asList(e1, e2));

        p1.getEnderecos().add(e1);
        p2.getEnderecos().add(e2);
        pessoaRepositories.saveAll(Arrays.asList(p1, p2));

    }

}
