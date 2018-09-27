package com.siswbrasil.ddd.repository.helper;

import com.siswbrasil.ddd.modelo.Pessoa;
import com.siswbrasil.ddd.repository.filtro.PessoaFiltro;

import java.util.List;

public interface PessoaRepositoryQueries {

    List<Pessoa> filtrar(PessoaFiltro filtro);
}
