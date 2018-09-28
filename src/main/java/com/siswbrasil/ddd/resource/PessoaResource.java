package com.siswbrasil.ddd.resource;

import com.siswbrasil.ddd.modelo.Pessoa;
import com.siswbrasil.ddd.modelo.Telefone;
import com.siswbrasil.ddd.servico.PessoaService;
import com.siswbrasil.ddd.servico.exception.TelefoneNaoEncontradoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pessoas")
public class PessoaResource {

    @Autowired
    private PessoaService pessoaService;

    @GetMapping("/{ddd}/{numero}")
    public ResponseEntity<Pessoa> buscarPorDddENumeroDoTelefone(@PathVariable("ddd")  String ddd,
                                                                @PathVariable("numero") String numero) throws TelefoneNaoEncontradoException {
        final Telefone telefone = new Telefone();
        telefone.setDdd(ddd);
        telefone.setNumero(numero);;

        final Pessoa pessoa = pessoaService.buscarPorTelefone(telefone);

        return new ResponseEntity<>(pessoa,HttpStatus.OK);

    }

    @ExceptionHandler({TelefoneNaoEncontradoException.class})
    public ResponseEntity<Erro> handleTelefoneNaoEncontradoException(TelefoneNaoEncontradoException e){
        return new ResponseEntity<>(new Erro(e.getMessage()),HttpStatus.NOT_FOUND);
    }

    class Erro {
        private final String erro;

        public Erro(String erro) {
            this.erro = erro;
        }

        public String getErro() {
            return erro;
        }
    }
}
