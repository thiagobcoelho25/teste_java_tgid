package com.example.teste_backend_java_tgid.models.dtos.outputs.cliente;

import com.example.teste_backend_java_tgid.models.dtos.outputs.transacao.TransacaoOutputDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ClienteOutputDTO {
    private Long id;

    private String nome;

    private String cpf;

    private Double saldo;

    private List<TransacaoOutputDTO> transacoes;

}
