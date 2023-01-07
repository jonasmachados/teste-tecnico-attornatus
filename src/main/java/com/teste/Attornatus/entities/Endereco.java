package com.teste.Attornatus.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
@Table(name = "tb_endereco")
@Entity
public class Endereco implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Campo logradouro é requerido.")
    @Length(min = 3, max = 50, message = "Campo logradouro deve ter entre 3 a 50 caracteres.")
    private String logradouro;

    @NotEmpty(message = "Campo cep é requerido.")
    @Pattern(regexp = "\\d{5}-\\d{3}", message = "Cep invalido.")
    private String cep;

    @NotEmpty(message = "Campo numero é requerido.")
    @Length(min = 1, max = 10, message = "Campo numero não é valido.")
    private String numero;

    @NotEmpty(message = "Campo cidade é requerido.")
    @Length(min = 2, max = 50, message = "Campo cidade deve ter entre 2 a 50 caracteres.")
    private String cidade;

    public Endereco() {
    }

    public Endereco(Long id, String logradouro, String cep, String numero, String cidade) {
        this.id = id;
        this.logradouro = logradouro;
        this.cep = cep;
        this.numero = numero;
        this.cidade = cidade;
    }

}
