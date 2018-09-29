package com.siswbrasil.tdd.repository.helper;

import com.siswbrasil.tdd.modelo.Pessoa;
import com.siswbrasil.tdd.repository.filtro.PessoaFiltro;

import java.util.List;

public interface PessoaRepositoryQueries {

    List<Pessoa> filtrar(PessoaFiltro filtro);
}
