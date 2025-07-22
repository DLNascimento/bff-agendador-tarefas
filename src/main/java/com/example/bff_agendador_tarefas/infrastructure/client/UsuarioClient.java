package com.example.bff_agendador_tarefas.infrastructure.client;

import com.example.bff_agendador_tarefas.business.dto.request.EnderecoDTORequest;
import com.example.bff_agendador_tarefas.business.dto.request.LoginDTORequest;
import com.example.bff_agendador_tarefas.business.dto.request.TelefoneDTORequest;
import com.example.bff_agendador_tarefas.business.dto.request.UsuarioDTORequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "usuario", url = "${usuario.url}")
public interface UsuarioClient {

    @GetMapping
    UsuarioDTORequest buscarUsuario(@RequestParam("email") String email,
                                    @RequestHeader("Authorization") String token);


    @PostMapping
    UsuarioDTORequest salvaUsuario(@RequestBody UsuarioDTORequest usuarioDTO);

    @PostMapping("/login")
    String logarUsuario(@RequestBody LoginDTORequest usuarioDTO);

    @PostMapping("/endereco")
    EnderecoDTORequest novoEndereco(@RequestBody EnderecoDTORequest dto,
                                    @RequestHeader("Authorization") String token);


    @PostMapping("/telefone")
    TelefoneDTORequest novoTelefone(@RequestBody TelefoneDTORequest dto,
                                    @RequestHeader("Authorization") String token);


    @DeleteMapping("/{email}")
    void deletaPorEmail(@PathVariable String email,
                        @RequestHeader("Authorization") String token);

    @PutMapping
    UsuarioDTORequest atualizaDadosUsuario(@RequestBody UsuarioDTORequest usuarioDTO,
                                           @RequestHeader("Authorization") String token);

    @PutMapping("/endereco")
    EnderecoDTORequest atualizaDadosEndereco(@RequestBody EnderecoDTORequest enderecoDTO,
                                             @RequestParam("id") Long id,
                                             @RequestHeader("Authorization") String token);

    @PutMapping("/telefone")
    TelefoneDTORequest atualizaDadosTelefone(@RequestBody TelefoneDTORequest telefoneDTO,
                                             @RequestParam("id") Long id,
                                             @RequestHeader("Authorization") String token);
}
