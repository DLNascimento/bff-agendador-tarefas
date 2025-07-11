package com.example.bff_agendador_tarefas.business.service;

import com.example.bff_agendador_tarefas.business.dto.request.TarefasDTORequest;
import com.example.bff_agendador_tarefas.business.dto.response.TarefasDTOResponse;
import com.example.bff_agendador_tarefas.infrastructure.client.TarefasClient;
import com.example.bff_agendador_tarefas.infrastructure.enums.StatusNotificacaoEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TarefasService {

    private final TarefasClient client;


    public TarefasDTOResponse cadastrarTarefa(String token, TarefasDTORequest dto) {
        return client.cadastrarTarefa(dto, token);
    }

    public List<TarefasDTOResponse> buscarEventosPorPeriodo(LocalDateTime dataInicial, LocalDateTime dataFinal, String token) {

        return client.buscarEventosPorPeriodo(dataInicial, dataFinal, token);

    }

    public List<TarefasDTOResponse> buscarEventosPorEmail(String token) {

        return client.buscarEventosPorEmail(token);

    }

    public void deletarPorId(String id, String token) {

        client.deletePorId(id, token);

    }

    public TarefasDTOResponse atualizaStatusNotificacao(StatusNotificacaoEnum status, String id, String token) {

        return client.atualizaStatusNotificacao(status, id, token);
    }

    public TarefasDTOResponse updateTarefas(TarefasDTORequest tarefasDTO, String id, String token) {

        return client.updateTarefa(tarefasDTO, id, token);

    }
}
