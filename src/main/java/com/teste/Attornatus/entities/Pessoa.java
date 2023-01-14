package com.teste.Attornatus.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
@AllArgsConstructor
@Table(name = "tb_pessoa")
@Entity
public class Pessoa implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Campo nome é requerido")
    @Length(min = 3, max = 30, message = "Campo nome deve ter entre 3 a 30 caracteres")
    private String nome;

    @NotNull(message = "Campo date é requerido")
    @JsonFormat(pattern = "dd/MM/yyyy", shape = Shape.STRING)
    private String dataNascimento;

    @ManyToMany
    private List<Endereco> enderecos = new ArrayList<>();

    public Pessoa() {
    }

    public Pessoa(Long id, String nome, String dataNascimento) {
        this.id = id;
        this.nome = nome;
        this.dataNascimento = dataNascimento;
    }

}
