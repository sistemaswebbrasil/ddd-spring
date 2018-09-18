package com.siswbrasil.ddd.servico;

import com.siswbrasil.ddd.modelo.Pessoa;
import com.siswbrasil.ddd.modelo.Telefone;
import com.siswbrasil.ddd.servico.exception.TelefoneNaoEncontradoException;
import com.siswbrasil.ddd.servico.exception.UnicidadeCpfException;
import com.siswbrasil.ddd.servico.exception.UnicidadeTelefoneException;

public interface PessoaService {
    Pessoa salvar(Pessoa pessoa) throws UnicidadeCpfException, UnicidadeTelefoneException;

    Pessoa buscarPorTelefone(Telefone telefone) throws TelefoneNaoEncontradoException;
}

