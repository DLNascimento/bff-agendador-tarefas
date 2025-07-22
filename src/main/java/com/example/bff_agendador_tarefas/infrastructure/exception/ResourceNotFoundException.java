package com.example.bff_agendador_tarefas.infrastructure.exception;

public class ResourceNotFoundException extends RuntimeException{

    public ResourceNotFoundException(String mensagem){
        super(mensagem);
    }
    public ResourceNotFoundException(String mensagem, Throwable causa){
        super(mensagem, causa);
    }

}
