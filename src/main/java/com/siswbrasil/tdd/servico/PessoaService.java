package com.siswbrasil.tdd.servico;

import com.siswbrasil.tdd.modelo.Pessoa;
import com.siswbrasil.tdd.modelo.Telefone;
import com.siswbrasil.tdd.servico.exception.TelefoneNaoEncontradoException;
import com.siswbrasil.tdd.servico.exception.UnicidadeCpfException;
import com.siswbrasil.tdd.servico.exception.UnicidadeTelefoneException;

public interface PessoaService {
    Pessoa salvar(Pessoa pessoa) throws UnicidadeCpfException, UnicidadeTelefoneException;

    Pessoa buscarPorTelefone(Telefone telefone) throws TelefoneNaoEncontradoException;
}

