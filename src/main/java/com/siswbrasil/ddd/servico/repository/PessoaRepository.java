package com.siswbrasil.ddd.servico.repository;

import com.siswbrasil.ddd.modelo.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa,Long> {

    Optional<Pessoa> findByCpf(String cpf);

    Optional<Pessoa> findByTelefoneDddAndTelefoneNumero(String ddd, String numero);
}
