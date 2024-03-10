package com.example.teste_backend_java_tgid.services;

import com.example.teste_backend_java_tgid.models.dtos.inputs.cliente.CreateClienteDTO;
import com.example.teste_backend_java_tgid.models.entities.Cliente;
import com.example.teste_backend_java_tgid.models.mappers.ClienteMapper;
import com.example.teste_backend_java_tgid.repositories.ClienteRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ClienteServiceTest {

    @Mock
    ClienteRepository clienteRepository;


    @Mock
    ClienteMapper clienteMapper;

    @InjectMocks
    ClienteService clienteService;

    @Test
    @DisplayName("Deve Retornar erro caso o cliente não exista!")
    void clienteNaoExiste() {
        Mockito.when(clienteRepository.findById(Mockito.anyLong())).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, () -> clienteService.findById(1L));
    }

    @Test
    @DisplayName("Deve Criar Um Cliente")
    void createCliente() {
        CreateClienteDTO dto = new CreateClienteDTO();
        dto.setSaldo(50.0);
        dto.setCpf("20838869092");
        dto.setNome("Thiago");

        Cliente cliente = new Cliente(1L, "Thiago", "20838869092", 50.0, null);

        Mockito.when(clienteRepository.save(Mockito.any(Cliente.class))).thenReturn(cliente);
        Mockito.when(clienteMapper.dtoToCliente(Mockito.any(CreateClienteDTO.class))).thenReturn(cliente);

        Cliente cliente_returned = clienteService.createCliente(dto);

        assertEquals(cliente_returned.getId(), cliente.getId());
    }
}