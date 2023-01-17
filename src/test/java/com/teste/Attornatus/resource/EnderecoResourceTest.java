package com.teste.Attornatus.resource;

import com.teste.Attornatus.entities.Endereco;
import com.teste.Attornatus.entities.Pessoa;
import com.teste.Attornatus.projection.EnderecoByPessoaProjection;
import com.teste.Attornatus.services.EnderecoService;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@SpringBootTest
public class EnderecoResourceTest {

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
    private EnderecoResource resource;

    @Mock
    private EnderecoService service;

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
        when(service.insert(any())).thenReturn(endereco);

        ResponseEntity<Endereco> response = resource.insert(endereco);

        assertNotNull(response);
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getHeaders().get("Location"));

    }

    @Test
    public void whenFindEndByPessoaThenReturnAddressList() {
        when(service.findEndByPessoa(anyInt())).thenReturn(List.of(enderecoByPessoaProjection));

        ResponseEntity<List<EnderecoByPessoaProjection>> response = resource.findEndByPessoa(1);

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(ResponseEntity.class, response.getClass());

        assertEquals(NUMERO, response.getBody().get(INDEX).getNumero());
        assertEquals(LOGRADOURO, response.getBody().get(INDEX).getLogradouro());
        assertEquals(CIDADE, response.getBody().get(INDEX).getCidade());
        assertEquals(CEP, response.getBody().get(INDEX).getCep());
    }

    private void startDB() {
        pessoa = new Pessoa(ID_PESSOA, NOME, DATA_NASCIMENTO);
        endereco = new Endereco(ID, LOGRADOURO, CEP, NUMERO, CIDADE);
        pessoa.getEnderecos().add(endereco);
    }

}
