package com.example.teste_backend_java_tgid.controllers;

import com.example.teste_backend_java_tgid.models.dtos.inputs.cliente.CreateClienteDTO;
import com.example.teste_backend_java_tgid.models.dtos.outputs.cliente.ClienteOutputDTO;
import com.example.teste_backend_java_tgid.models.entities.Cliente;
import com.example.teste_backend_java_tgid.services.ClienteService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @PostMapping
    public ResponseEntity<String> createAndReturnTeam(@RequestBody @Valid CreateClienteDTO cliente_create_dto){
        Cliente cliente_criado = clienteService.createCliente(cliente_create_dto);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(cliente_criado.getId()).toUri();
        return ResponseEntity.created(location).body("Cliente Criado Com Sucesso!!!\nO ID Do Cliente e %d".formatted(cliente_criado.getId()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteOutputDTO> getClientById(@PathVariable Long id){
        return ResponseEntity.ok(clienteService.findById(id));
    }
}
