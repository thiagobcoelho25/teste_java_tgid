package com.example.teste_backend_java_tgid.models.dtos.outputs.taxa;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TaxaOutputDTO {
    private Long id;
    private String nome;
    private Double taxa;
}
