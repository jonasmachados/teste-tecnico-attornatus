package com.teste.Attornatus.resource;

import com.teste.Attornatus.entities.Endereco;
import com.teste.Attornatus.entities.Pessoa;
import com.teste.Attornatus.services.PessoaService;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@SpringBootTest
public class PessoaResourceTest {

    private static final Integer INDEX = 0;
    private static final Long ID = 1L;
    private static final String NOME = "Jose";
    private static final String DATA_NASCIMENTO = "06/10/1986";

    @InjectMocks
    private PessoaResource resource;

    @Mock
    private PessoaService service;

    private Pessoa pessoa;
    private Endereco endereco;
    private Optional<Pessoa> optionalPessoa;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        startDB();
    }

    @Test
    public void whenFindAllThenReturnAListOfPeoples() {
        when(service.findAll()).thenReturn(List.of(pessoa));

        ResponseEntity<List<Pessoa>> response = resource.findAll();

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(ResponseEntity.class, response.getClass());
        //assertEquals(ArrayList.class, response.getBody().getClass());
        assertEquals(Pessoa.class, response.getBody().get(INDEX).getClass());

        assertEquals(ID, response.getBody().get(INDEX).getId());
        assertEquals(NOME, response.getBody().get(INDEX).getNome());
        assertEquals(DATA_NASCIMENTO, response.getBody().get(INDEX).getDataNascimento());
    }

    @Test
    public void whenFindByIdThenReturnSuccess() {
        when(service.findById(anyLong())).thenReturn(pessoa);

        ResponseEntity<Pessoa> response = resource.findById(ID);

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(Pessoa.class, response.getBody().getClass());

        assertEquals(ID, response.getBody().getId());
        assertEquals(NOME, response.getBody().getNome());
        assertEquals(DATA_NASCIMENTO, response.getBody().getDataNascimento());

    }

    @Test
    public void whenInsertThenReturnNewPeople() {
        when(service.insert(any())).thenReturn(pessoa);

        ResponseEntity<Pessoa> response = resource.insert(pessoa);

        assertNotNull(response);
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getHeaders().get("Location"));
    }

    @Test
    public void whenUpdateThenReturnSuccess() {
        when(service.update(ID, pessoa)).thenReturn(pessoa);

        ResponseEntity<Pessoa> response = resource.update(ID, pessoa);

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(Pessoa.class, response.getBody().getClass());

        assertEquals(ID, response.getBody().getId());
        assertEquals(NOME, response.getBody().getNome());
        assertEquals(DATA_NASCIMENTO, response.getBody().getDataNascimento());

    }

    @Test
    public void whenAddEnderecoThenReturnSuccess() {
        when(service.addEndereco(ID, endereco)).thenReturn(pessoa);

        Pessoa response = resource.addEndereco(ID, endereco);

        assertNotNull(response);
        assertEquals(Pessoa.class, response.getClass());
        assertEquals(ArrayList.class, response.getEnderecos().getClass());
        assertEquals(Endereco.class, endereco.getClass());

        assertEquals(ID, response.getId());
        assertEquals(NOME, response.getNome());
        assertEquals(DATA_NASCIMENTO, response.getDataNascimento());
    }

    private void startDB() {
        pessoa = new Pessoa(ID, NOME, DATA_NASCIMENTO);
        endereco = new Endereco(1L, "Avenida Exterior", "01015-100", "98", "SP");
        optionalPessoa = Optional.of(new Pessoa(ID, NOME, DATA_NASCIMENTO));
    }

}
