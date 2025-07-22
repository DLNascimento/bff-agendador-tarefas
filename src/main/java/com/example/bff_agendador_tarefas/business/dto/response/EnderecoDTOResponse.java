package com.example.bff_agendador_tarefas.business.dto.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EnderecoDTOResponse {


    private String rua;
    private Long numero;
    private String complemento;
    private String cidade;
    private String estado;
    private String cep;

}
