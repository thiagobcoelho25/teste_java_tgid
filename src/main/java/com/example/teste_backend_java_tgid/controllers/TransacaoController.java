package com.example.teste_backend_java_tgid.controllers;

import com.example.teste_backend_java_tgid.models.dtos.inputs.cliente.CreateClienteDTO;
import com.example.teste_backend_java_tgid.models.dtos.inputs.transacao.CreateTransacaoDTO;
import com.example.teste_backend_java_tgid.models.dtos.outputs.transacao.TransacaoOutputDTO;
import com.example.teste_backend_java_tgid.models.entities.Cliente;
import com.example.teste_backend_java_tgid.services.TransacaoService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/api/transacao")
public class TransacaoController {

    private final TransacaoService transacaoService;

    public TransacaoController(TransacaoService transacaoService) {
        this.transacaoService = transacaoService;
    }

    @PostMapping
    public ResponseEntity<String> createAndReturnTeam(@RequestBody @Valid CreateTransacaoDTO create_transacao_dto){
        TransacaoOutputDTO transacaoOutputDTO = transacaoService.depositar(create_transacao_dto.getCliente_id(),
                create_transacao_dto.getEmpresa_id(), create_transacao_dto.getValor_deposito());
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(transacaoOutputDTO.getId()).toUri();
        return ResponseEntity.created(location).body("Transação Criado Com Sucesso!!!\nO ID Da Transação é %d".formatted(transacaoOutputDTO.getId()));
    }
}
