package com.example.bff_agendador_tarefas.business.service;

import com.example.bff_agendador_tarefas.business.dto.response.TarefasDTOResponse;
import com.example.bff_agendador_tarefas.infrastructure.client.EmailClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailService {

     private final EmailClient client;

     public void enviarEmail(TarefasDTOResponse dtoResponse){

          client.enviarEmail(dtoResponse);
     }
}
