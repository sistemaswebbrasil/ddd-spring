package com.siswbrasil.tdd.resource.exception;

import com.siswbrasil.tdd.servico.exception.TelefoneNaoEncontradoException;
import com.siswbrasil.tdd.servico.exception.UnicidadeCpfException;
import com.siswbrasil.tdd.servico.exception.UnicidadeTelefoneException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ResourceExceptionHandler {

    @ExceptionHandler({UnicidadeTelefoneException.class})
    public ResponseEntity<Erro> handleUnicidadeTelefoneException(UnicidadeTelefoneException e) {
        return new ResponseEntity<>(new Erro(e.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({UnicidadeCpfException.class})
    public ResponseEntity<Erro> handleUnicidadeCpfException(UnicidadeCpfException e) {
        return new ResponseEntity<>(new Erro(e.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({TelefoneNaoEncontradoException.class})
    public ResponseEntity<Erro> handleTelefoneNaoEncontradoException(TelefoneNaoEncontradoException e) {
        return new ResponseEntity<>(new Erro(e.getMessage()), HttpStatus.NOT_FOUND);
    }
}
