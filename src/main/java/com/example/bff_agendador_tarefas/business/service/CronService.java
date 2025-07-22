package com.example.bff_agendador_tarefas.business.service;

import com.example.bff_agendador_tarefas.business.dto.request.LoginDTORequest;
import com.example.bff_agendador_tarefas.business.dto.response.TarefasDTOResponse;
import com.example.bff_agendador_tarefas.infrastructure.enums.StatusNotificacaoEnum;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CronService {

    private final TarefasService tarefasService;
    private final EmailService emailService;
    private final UsuarioService usuarioService;


    @Value("${usuario.email}")
    private String email;

    @Value("${usuario.senha}")
    private String senha;

    @Scheduled(cron = "${cron.horario}")
    public void buscarTarefasProximaHora() {

        String token = login(paraLoginDTORequest());



        LocalDateTime proximaHora = LocalDateTime.now().plusHours(1);
        LocalDateTime proximaHoraMaisCincoMinutos = LocalDateTime.now().plusHours(1).plusMinutes(5);

        log.info("Iniciada a busca por tarefas");
        List<TarefasDTOResponse> listaTarefas = tarefasService.buscarEventosPorPeriodo(proximaHora, proximaHoraMaisCincoMinutos, token);

        log.info("Tarefa encontrada: " + listaTarefas);

        listaTarefas.forEach(tarefa -> {
            log.info("enviando email para o email: " + tarefa.getEmailUsuario());
            emailService.enviarEmail(tarefa);
            tarefasService.atualizaStatusNotificacao(StatusNotificacaoEnum.NOTIFICADO,
                    tarefa.getId(),
                    token);
        });
        log.info("Finalizando o servico");
    }

    public String login(LoginDTORequest dtoRequest) {

        return usuarioService.logarUsuario(dtoRequest);
    }

    public LoginDTORequest paraLoginDTORequest() {

        return LoginDTORequest.builder()
                .email(email)
                .senha(senha)
                .build();
    }

}
