package com.sistema.academia.config.exception;

public class RecursoNaoEncontradoException
        extends RuntimeException{

    public RecursoNaoEncontradoException(
            String mensagem){

        super(mensagem);
    }

}
