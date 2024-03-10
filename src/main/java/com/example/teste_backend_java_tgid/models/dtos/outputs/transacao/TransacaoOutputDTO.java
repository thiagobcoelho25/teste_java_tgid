package com.example.teste_backend_java_tgid.models.dtos.outputs.transacao;
import com.example.teste_backend_java_tgid.models.dtos.outputs.taxa.TaxaOutputDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TransacaoOutputDTO {
    private Long id;

    private Double quantidade;

    private TaxaOutputDTO tipoTaxa;
}
