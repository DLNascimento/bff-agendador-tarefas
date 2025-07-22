package com.example.bff_agendador_tarefas.infrastructure.client;

import com.example.bff_agendador_tarefas.business.dto.request.TarefasDTORequest;
import com.example.bff_agendador_tarefas.business.dto.response.TarefasDTOResponse;
import com.example.bff_agendador_tarefas.infrastructure.enums.StatusNotificacaoEnum;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@FeignClient(name = "agendador-tarefas", url = "${agendador-tarefas.url}")
public interface TarefasClient {

    @PostMapping
    TarefasDTOResponse cadastrarTarefa(@RequestBody TarefasDTORequest tarefasDTO,
                                       @RequestHeader("Authorization") String token);


    @GetMapping("/eventos")
    List<TarefasDTOResponse> buscarEventosPorPeriodo(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataInicial,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataFinal,
            @RequestHeader("Authorization") String token);


    @GetMapping
    List<TarefasDTOResponse> buscarEventosPorEmail(@RequestHeader("Authorization") String token);


    @DeleteMapping
    void deletePorId(@RequestParam("id") String id,
                     @RequestHeader("Authorization") String token);


    @PatchMapping
    TarefasDTOResponse atualizaStatusNotificacao(@RequestParam("status") StatusNotificacaoEnum status,
                                                 @RequestParam("id") String id,
                                                 @RequestHeader("Authorization") String token);

    @PutMapping
    TarefasDTOResponse updateTarefa(@RequestBody TarefasDTORequest dto,
                                    @RequestParam("id") String id,
                                    @RequestHeader("Authorization") String token);
}
