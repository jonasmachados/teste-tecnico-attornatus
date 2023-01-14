package com.teste.Attornatus.services;

import com.teste.Attornatus.entities.Endereco;
import com.teste.Attornatus.entities.Pessoa;
import com.teste.Attornatus.repositories.PessoaRepository;
import com.teste.Attornatus.services.exceptions.ResourceNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class PessoaServiceTest {

    private static final Integer INDEX = 0;
    private static final Long ID = 1L;
    private static final String NOME = "Jose";
    private static final String DATA_NASCIMENTO = "06/10/1986";

    @InjectMocks
    private PessoaService service;

    @Mock
    private PessoaRepository repository;

    private Pessoa pessoa;
    private Endereco endereco;
    private Optional<Pessoa> optionalPessoa;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        startDB();
    }

    @Test
    void whenFindAllThenReturnAnListOfPeoples() {

        when(repository.findAll()).thenReturn(List.of(pessoa));

        List<Pessoa> response = service.findAll();

        assertNotNull(response);
        assertEquals(1, response.size());
        assertEquals(Pessoa.class, response.get(INDEX).getClass());

        assertEquals(ID, response.get(INDEX).getId());
        assertEquals(NOME, response.get(INDEX).getNome());
        assertEquals(DATA_NASCIMENTO, response.get(INDEX).getDataNascimento());
    }

    @Test
    public void whenFindByIdThenReturnAnPeopleInstance() {

        when(repository.findById(anyLong())).thenReturn(optionalPessoa);

        Pessoa response = service.findById(ID);

        assertNotNull(response);

        assertEquals(Pessoa.class, response.getClass());
        assertEquals(ID, response.getId());
        assertEquals(NOME, response.getNome());
        assertEquals(DATA_NASCIMENTO, response.getDataNascimento());
    }

    @Test
    void whenFindByIdThenReturnAnObjectNotFoundException() {

        when(repository.findById(anyLong()))
                .thenThrow(new ResourceNotFoundException((anyLong())));

        try {
            service.findById(ID);
        } catch (Exception ex) {
            assertEquals(ResourceNotFoundException.class, ex.getClass());
            assertEquals("Resource not found. Id 0", ex.getMessage());
        }

    }

    @Test
    void whenInsertThenReturnSuccess() {
        when(repository.save(any())).thenReturn(pessoa);

        Pessoa response = service.insert(pessoa);

        assertNotNull(response);
        assertEquals(Pessoa.class, response.getClass());
        assertEquals(ID, response.getId());
        assertEquals(NOME, response.getNome());
        assertEquals(DATA_NASCIMENTO, response.getDataNascimento());
    }

    @Test
    public void whenUpdateThenReturnSuccess() {
        when(repository.findById(anyLong())).thenReturn(optionalPessoa);
        when(repository.save(any())).thenReturn(pessoa);

        Pessoa response = service.update(1L, pessoa);

        assertNotNull(response);
        assertEquals(Pessoa.class, response.getClass());
        assertEquals(ID, response.getId());
        assertEquals(NOME, response.getNome());
        assertEquals(DATA_NASCIMENTO, response.getDataNascimento());
    }

    @Test
    void whenUpdateThenReturnAnIdNotFound() {
        when(repository.findById(anyLong())).thenReturn(optionalPessoa);

        try {
            optionalPessoa.get().setId(2L);
            service.insert(pessoa);
        } catch (Exception ex) {
            assertEquals(ResourceNotFoundException.class, ex.getClass());
            assertEquals("Resource not found. Id 2", ex.getMessage());
        }
    }

    @Test
    public void whenAddEnderecoThenReturnSuccess() {
        when(repository.findById(anyLong())).thenReturn(optionalPessoa);
        when(repository.save(any())).thenReturn(pessoa);

        Pessoa response = service.addEndereco(ID, endereco);

        assertNotNull(response);
        assertEquals(Pessoa.class, response.getClass());
        assertEquals(ArrayList.class, response.getEnderecos().getClass());
        assertEquals(Endereco.class, endereco.getClass());

        assertEquals(ID, response.getId());
        assertEquals(NOME, response.getNome());
        assertEquals(DATA_NASCIMENTO, response.getDataNascimento());
    }

    @Test
    public void whenAddEnderecoThenReturnAnIdPeopleNotFound() {
        when(repository.findById(anyLong())).thenReturn(optionalPessoa);

        try {
            service.addEndereco(2L, endereco);
        } catch (Exception ex) {
            assertEquals(ResourceNotFoundException.class, ex.getClass());
            assertEquals("Resource not found. Id 2", ex.getMessage());
        }
    }

    private void startDB() {
        pessoa = new Pessoa(ID, NOME, DATA_NASCIMENTO);
        endereco = new Endereco(1L, "Avenida Exterior", "01015-100", "98", "SP");
        optionalPessoa = Optional.of(new Pessoa(ID, NOME, DATA_NASCIMENTO));
    }

}
