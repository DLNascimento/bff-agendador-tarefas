package com.example.bff_agendador_tarefas.infrastructure.exception;


public class ConflictException extends RuntimeException {

    public ConflictException(String mensagem){
        super(mensagem);
    }
    public ConflictException(String mensagem, Throwable causa){
        super(mensagem, causa);
    }

}
