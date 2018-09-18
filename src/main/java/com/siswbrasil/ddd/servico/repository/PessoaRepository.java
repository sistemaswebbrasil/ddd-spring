package com.siswbrasil.ddd.servico.repository;

import com.siswbrasil.ddd.modelo.Pessoa;
import java.util.Optional;

public interface PessoaRepository {
    Pessoa save(Pessoa pessoa);

    Optional<Pessoa> findByCpf(String cpf);

    Optional<Pessoa> findByTelefoneDddAndTelefoneNumero(String ddd, String numero);
}
