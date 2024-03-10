package com.example.teste_backend_java_tgid.models.dtos.inputs.transacao;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateTransacaoDTO {

    @NotNull
    private Long cliente_id;

    @NotNull
    private Long empresa_id;

    @NotNull
    @Min(message = "Valor não pode ser menor ou igual a zero", value = 0)
    private Double valor_deposito;

}
