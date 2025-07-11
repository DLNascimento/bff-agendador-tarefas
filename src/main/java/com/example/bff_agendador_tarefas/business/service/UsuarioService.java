package com.example.bff_agendador_tarefas.business.service;

import com.example.bff_agendador_tarefas.business.dto.request.EnderecoDTORequest;
import com.example.bff_agendador_tarefas.business.dto.request.LoginDTORequest;
import com.example.bff_agendador_tarefas.business.dto.request.TelefoneDTORequest;
import com.example.bff_agendador_tarefas.business.dto.request.UsuarioDTORequest;
import com.example.bff_agendador_tarefas.infrastructure.client.UsuarioClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioClient client;

    public UsuarioDTORequest salvaUsuario(UsuarioDTORequest usuarioDTO) {

        return client.salvaUsuario(usuarioDTO);

    }

    public String logarUsuario(LoginDTORequest usuarioDTO){
        return client.logarUsuario(usuarioDTO);
    }


    public UsuarioDTORequest buscarUsuario(String email, String token) {

        return client.buscarUsuario(email, token);
    }


    public void deletaPorEmail(String email, String token) {

        client.deletaPorEmail(email, token);
    }

    public UsuarioDTORequest atualizaDadosUsuario(String token, UsuarioDTORequest usuarioDTO) {

        return client.atualizaDadosUsuario(usuarioDTO, token);

    }

    public EnderecoDTORequest atualizaDadosEndereco(Long idEndereco, EnderecoDTORequest dto, String token) {

        return client.atualizaDadosEndereco(dto, idEndereco, token);

    }

    public TelefoneDTORequest atualizaDadosTelefone(Long idTelefone, TelefoneDTORequest dto, String token) {

        return client.atualizaDadosTelefone(dto, idTelefone, token);
    }

    public EnderecoDTORequest cadastroNovoEndereco(String token, EnderecoDTORequest dto) {

        return client.novoEndereco(dto, token);
    }

    public TelefoneDTORequest cadastroNovoTelefone(String token, TelefoneDTORequest dto) {

        return client.novoTelefone(dto, token);
    }

}
