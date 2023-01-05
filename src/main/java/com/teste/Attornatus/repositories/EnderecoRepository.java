package com.teste.Attornatus.repositories;

import com.teste.Attornatus.entities.Endereco;
import java.util.List;
import com.teste.Attornatus.projection.EnderecoByPessoaProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface EnderecoRepository extends JpaRepository<Endereco, Long> {

    @Query(value = "SELECT CEP,CIDADE,LOGRADOURO,NUMERO FROM TB_ENDERECO"
            + " INNER JOIN TB_PESSOA_ENDERECOS"
            + " ON TB_PESSOA_ENDERECOS .ENDERECOS_ID = TB_ENDERECO.ID"
            + " WHERE PESSOA_ID = :id", nativeQuery = true)
    List<EnderecoByPessoaProjection> findEndByPessoa(int id);

}
