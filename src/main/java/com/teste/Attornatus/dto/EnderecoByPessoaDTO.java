package com.teste.Attornatus.dto;

import com.teste.Attornatus.projection.EnderecoByPessoaProjection;

public class EnderecoByPessoaDTO {

    private String cep;
    private String cidade;
    private String logradouro;
    private String numero;

    public EnderecoByPessoaDTO() {
    }

    public EnderecoByPessoaDTO(String cep, String cidade, String logradouro, String numero) {
        this.cep = cep;
        this.cidade = cidade;
        this.logradouro = logradouro;
        this.numero = numero;
    }

    public EnderecoByPessoaDTO(EnderecoByPessoaProjection projection) {
        cep = projection.getCep();
        cidade = projection.getCidade();
        logradouro = projection.getLogradouro();
        numero = projection.getNumero();
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

}
