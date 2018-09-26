package com.siswbrasil.ddd.repository;

import com.siswbrasil.ddd.modelo.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa,Long> {

    Optional<Pessoa> findByCpf(String cpf);

    @Query("SELECT bean FROM Pessoa bean WHERE 1=1")
    Optional<Pessoa> findByTelefoneDddAndTelefoneNumero(String ddd, String numero);
}
