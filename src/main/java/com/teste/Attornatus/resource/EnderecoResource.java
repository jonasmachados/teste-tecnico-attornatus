package com.teste.Attornatus.resource;

import com.teste.Attornatus.entities.Endereco;
import com.teste.Attornatus.projection.EnderecoByPessoaProjection;
import com.teste.Attornatus.services.EnderecoService;
import java.net.URI;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping(value = "/endereco")
public class EnderecoResource {

    @Autowired
    private EnderecoService service;

    @PostMapping
    public ResponseEntity<Endereco> insert(@RequestBody Endereco obj) {
        obj = service.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).body(obj);
    }
    
    @GetMapping("/findByPessoa/{id}")
    public ResponseEntity<List<EnderecoByPessoaProjection>> findEndByPessoa(@PathVariable("id") int id) {
        List<EnderecoByPessoaProjection> list = service.findEndByPessoa(id);
        return ResponseEntity.ok().body(list);
    }
    
}
