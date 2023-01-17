package com.teste.Attornatus.services;

import com.teste.Attornatus.entities.Endereco;
import com.teste.Attornatus.entities.Pessoa;
import com.teste.Attornatus.projection.EnderecoByPessoaProjection;
import com.teste.Attornatus.repositories.EnderecoRepository;
import java.util.List;
import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class EnderecoServiceTest {

    private static final Integer INDEX = 0;
    private static final Long ID = 1L;
    private static final String LOGRADOURO = "Avenida Castelo Branco";
    private static final String CEP = "01015-100";
    private static final String NUMERO = "98";
    private static final String CIDADE = "SP";
    
    private static final Long ID_PESSOA = 1L;
    private static final String NOME = "Jose";
    private static final String DATA_NASCIMENTO = "06/10/1986";

    @InjectMocks
    private EnderecoService service;

    @Mock
    private EnderecoRepository repository;

    private Pessoa pessoa;
    private Endereco endereco;

    EnderecoByPessoaProjection enderecoByPessoaProjection = new EnderecoByPessoaProjection() {

        public String getCep() {
            return CEP;
        }

        public String getCidade() {
            return CIDADE;
        }

        public String getLogradouro() {
            return LOGRADOURO;
        }

        public String getNumero() {
            return NUMERO;
        }

    };

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        startDB();
    }

    @Test
    public void whenInsertThenReturnAddressList() {
        when(repository.save(any())).thenReturn(endereco);

        Endereco response = service.insert(endereco);

        assertNotNull(response);
        assertEquals(Endereco.class, response.getClass());
        assertEquals(ID, response.getId());
        assertEquals(LOGRADOURO, response.getLogradouro());
        assertEquals(CEP, response.getCep());
        assertEquals(NUMERO, response.getNumero());
        assertEquals(CIDADE, response.getCidade());
    }

    @Test
    public void whenFindEndByPessoaThenReturnAddressList() {
        when(repository.findEndByPessoa(anyInt())).thenReturn(List.of(enderecoByPessoaProjection));

        List<EnderecoByPessoaProjection> response = service.findEndByPessoa(1);
        assertNotNull(response);
        assertEquals(1, response.size());

        assertEquals(NUMERO, response.get(INDEX).getNumero());
        assertEquals(LOGRADOURO, response.get(INDEX).getLogradouro());
        assertEquals(CIDADE, response.get(INDEX).getCidade());
        assertEquals(CEP, response.get(INDEX).getCep());

    }

    private void startDB() {
        pessoa = new Pessoa(ID_PESSOA, NOME, DATA_NASCIMENTO);
        endereco = new Endereco(ID, LOGRADOURO, CEP, NUMERO, CIDADE);
        pessoa.getEnderecos().add(endereco);
    }

}
