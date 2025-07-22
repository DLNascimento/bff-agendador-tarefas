package com.example.bff_agendador_tarefas.controller;


import com.example.bff_agendador_tarefas.business.dto.request.EnderecoDTORequest;
import com.example.bff_agendador_tarefas.business.dto.request.LoginDTORequest;
import com.example.bff_agendador_tarefas.business.dto.request.TelefoneDTORequest;
import com.example.bff_agendador_tarefas.business.dto.request.UsuarioDTORequest;
import com.example.bff_agendador_tarefas.business.service.UsuarioService;
import com.example.bff_agendador_tarefas.infrastructure.security.SecurityConfig;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuario")
@RequiredArgsConstructor
@Tag(name = "Usuário", description = "Cadastro e login de usuários")
@SecurityRequirement(name = SecurityConfig.SECURITY_SCHEME)
public class UsuarioController {

    private final UsuarioService usuarioService;


    @PostMapping
    @Operation(summary = "Salvar Usuários", description = "Cria um novo usuário")
    @ApiResponse(responseCode = "200", description = "Usuário salvo com sucesso")
    @ApiResponse(responseCode = "401", description = "Credenciais inválidas")
    @ApiResponse(responseCode = "409", description = "Usuário já cadastrado")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public ResponseEntity<UsuarioDTORequest> salvaUsuario(@RequestBody UsuarioDTORequest usuarioDTO) {

        return ResponseEntity.ok(usuarioService.salvaUsuario(usuarioDTO));

    }

    @PostMapping("/login")
    @Operation(summary = "Login de Usuários", description = "Login do usuário")
    @ApiResponse(responseCode = "200", description = "Usuário logado com sucesso")
    @ApiResponse(responseCode = "401", description = "Credenciais inválidas")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public String logarUsuario(@RequestBody LoginDTORequest usuarioDTO) {

        return usuarioService.logarUsuario(usuarioDTO);

    }

    @PostMapping("/endereco")
    @Operation(summary = "Salva Enderecos de Usuários", description = "Salva um novo endereco de usuário")
    @ApiResponse(responseCode = "200", description = "Endereco salvo com sucesso")
    @ApiResponse(responseCode = "401", description = "Credenciais inválidas")
    @ApiResponse(responseCode = "403", description = "Usuário não encontrado")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public ResponseEntity<EnderecoDTORequest> novoEndereco(@RequestBody EnderecoDTORequest dto,
                                                           @RequestHeader(name = "Authorization", required = false) String token) {
        return ResponseEntity.ok(usuarioService.cadastroNovoEndereco(token, dto));
    }

    @PostMapping("/telefone")
    @Operation(summary = "Salva Telefones de Usuários", description = "Salva um novo telefone de usuário")
    @ApiResponse(responseCode = "200", description = "Telefone salvo com sucesso")
    @ApiResponse(responseCode = "401", description = "Credenciais inválidas")
    @ApiResponse(responseCode = "403", description = "Usuário não encontrado")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public ResponseEntity<TelefoneDTORequest> novoTelefone(@RequestBody TelefoneDTORequest dto,
                                                           @RequestHeader(name = "Authorization", required = false) String token) {
        return ResponseEntity.ok(usuarioService.cadastroNovoTelefone(token, dto));

    }


    @GetMapping
    @Operation(summary = "Buscar dados de Usuários por email",
            description = "Busca os dados do usuário")
    @ApiResponse(responseCode = "200", description = "Usuário encontrado")
    @ApiResponse(responseCode = "401", description = "Credenciais inválidas")
    @ApiResponse(responseCode = "403", description = "Usuário não encontrado")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public ResponseEntity<UsuarioDTORequest> buscarUsuario(@RequestParam("email") String email,
                                                           @RequestHeader(name = "Authorization", required = false) String token) {
        return ResponseEntity.ok(usuarioService.buscarUsuario(email, token));
    }

    @DeleteMapping("/{email}")
    @Operation(summary = "Deleta Usuários por id", description = "Deleta usuário")
    @ApiResponse(responseCode = "200", description = "Usuário deletado com sucesso")
    @ApiResponse(responseCode = "401", description = "Credenciais inválidas")
    @ApiResponse(responseCode = "403", description = "Usuário não encontrado")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public ResponseEntity<Void> deletaPorEmail(@PathVariable String email,
                                               @RequestHeader(name = "Authorization", required = false) String token) {
        usuarioService.deletaPorEmail(email, token);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    @Operation(summary = "Atualizar dados de Usuários", description = "Atualizar dados de Usuário")
    @ApiResponse(responseCode = "200", description = "Usuário atualizado com sucesso")
    @ApiResponse(responseCode = "401", description = "Credenciais inválidas")
    @ApiResponse(responseCode = "403", description = "Usuário não encontrado")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public ResponseEntity<UsuarioDTORequest> atualizaDadosUsuario(@RequestBody UsuarioDTORequest usuarioDTO,
                                                                  @RequestHeader(name = "Authorization", required = false) String token) {

        return ResponseEntity.ok(usuarioService.atualizaDadosUsuario(token, usuarioDTO));
    }

    @PutMapping("/endereco")
    @Operation(summary = "Atualizar enderecos de Usuários", description = "Atualiza endereco de usuário")
    @ApiResponse(responseCode = "200", description = "Endereco atualizado com sucesso")
    @ApiResponse(responseCode = "401", description = "Credenciais inválidas")
    @ApiResponse(responseCode = "403", description = "Usuário não encontrado")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public ResponseEntity<EnderecoDTORequest> atualizaDadosEndereco(@RequestBody EnderecoDTORequest enderecoDTO,
                                                                    @RequestParam("id") Long id,
                                                                    @RequestHeader(name = "Authorization", required = false) String token) {
        return ResponseEntity.ok(usuarioService.atualizaDadosEndereco(id, enderecoDTO, token));
    }

    @PutMapping("/telefone")
    @Operation(summary = "Atualizar telefones de Usuários", description = "Atualiza telefone de usuário")
    @ApiResponse(responseCode = "200", description = "Telefone atualizado com sucesso")
    @ApiResponse(responseCode = "401", description = "Credenciais inválidas")
    @ApiResponse(responseCode = "403", description = "Usuário não encontrado")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public ResponseEntity<TelefoneDTORequest> atualizaDadosTelefone(@RequestBody TelefoneDTORequest telefoneDTO,
                                                                    @RequestParam("id") Long id,
                                                                    @RequestHeader(name = "Authorization", required = false) String token) {
        return ResponseEntity.ok(usuarioService.atualizaDadosTelefone(id, telefoneDTO, token));
    }

}
