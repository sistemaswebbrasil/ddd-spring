package com.siswbrasil.tdd.servico;

import com.siswbrasil.tdd.modelo.Pessoa;
import com.siswbrasil.tdd.modelo.Telefone;
import com.siswbrasil.tdd.repository.filtro.PessoaFiltro;
import com.siswbrasil.tdd.servico.exception.TelefoneNaoEncontradoException;
import com.siswbrasil.tdd.servico.exception.UnicidadeCpfException;
import com.siswbrasil.tdd.servico.exception.UnicidadeTelefoneException;

import java.util.List;

public interface PessoaService {
    Pessoa salvar(Pessoa pessoa) throws UnicidadeCpfException, UnicidadeTelefoneException;

    Pessoa buscarPorTelefone(Telefone telefone) throws TelefoneNaoEncontradoException;

    List<Pessoa> filtrar(PessoaFiltro filtro);
}

