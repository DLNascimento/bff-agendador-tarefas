package com.example.bff_agendador_tarefas.infrastructure.client.config;

import com.example.bff_agendador_tarefas.infrastructure.exception.BusinessException;
import com.example.bff_agendador_tarefas.infrastructure.exception.ConflictException;
import com.example.bff_agendador_tarefas.infrastructure.exception.ResourceNotFoundException;
import com.example.bff_agendador_tarefas.infrastructure.exception.UnauthorizedException;
import feign.Response;
import feign.codec.ErrorDecoder;

public class FeignError implements ErrorDecoder {
    @Override
    public Exception decode(String s, Response response) {
        switch (response.status()){
            case 409:
                return new ConflictException("Erro atributo já existente ");
            case 403:
                return new ResourceNotFoundException("Erro atributo não encontrado ");
            case 401:
                return new UnauthorizedException("Erro usuário não autorizado ");
            default:
                return new BusinessException("Erro de servidor");
        }
    }
}
