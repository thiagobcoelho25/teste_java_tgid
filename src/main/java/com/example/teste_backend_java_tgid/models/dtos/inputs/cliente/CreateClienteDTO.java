package com.example.teste_backend_java_tgid.models.dtos.inputs.cliente;

import com.example.teste_backend_java_tgid.annotations.CPF;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateClienteDTO {

    @NotBlank(message = "Nome Não Pode Ser Vazio!")
    private String nome;

    @NotNull(message = "Saldo Não Pode Ser Nulo!")
    @Min(message = "Valor deve ser maior ou igual a zero!", value = 0)
    private Double saldo;


    @NotBlank(message = "CPF Não Pode Ser Nulo!")
    @CPF(message = "CPF Invalido!")
    private String cpf;

}
