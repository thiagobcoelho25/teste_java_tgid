package com.example.teste_backend_java_tgid.services;

import com.example.teste_backend_java_tgid.models.dtos.inputs.cliente.CreateClienteDTO;
import com.example.teste_backend_java_tgid.models.dtos.outputs.cliente.ClienteOutputDTO;
import com.example.teste_backend_java_tgid.models.entities.Cliente;
import com.example.teste_backend_java_tgid.models.mappers.ClienteMapper;
import com.example.teste_backend_java_tgid.repositories.ClienteRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;
    private final ClienteMapper clienteMapper;

    public ClienteService(ClienteRepository clienteRepository, ClienteMapper clienteMapper) {
        this.clienteRepository = clienteRepository;
        this.clienteMapper = clienteMapper;
    }

    public Cliente createCliente(CreateClienteDTO cliente_dto){
        Cliente cliente_to_save = clienteMapper.dtoToCliente(cliente_dto);
        return clienteRepository.save(cliente_to_save);
    }

    public ClienteOutputDTO findById(Long id) {
        Cliente cliente = clienteRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Cliente Não Encontrado Para o ID Informado!"));
        return clienteMapper.entitytoDTO(cliente);


    }
}
