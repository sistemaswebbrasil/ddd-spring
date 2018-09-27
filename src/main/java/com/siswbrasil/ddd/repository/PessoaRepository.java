package com.siswbrasil.ddd.repository;

import com.siswbrasil.ddd.modelo.Pessoa;
import com.siswbrasil.ddd.repository.helper.PessoaRepositoryQueries;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa,Long>, PessoaRepositoryQueries {

    Optional<Pessoa> findByCpf(String cpf);

    @Query("SELECT bean FROM Pessoa bean JOIN bean.telefones tele WHERE tele.ddd = :ddd AND tele.numero = :numero")
    Optional<Pessoa> findByTelefoneDddAndTelefoneNumero(@Param("ddd") String ddd,@Param("numero") String numero);
}
