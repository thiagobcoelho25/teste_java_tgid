package com.example.teste_backend_java_tgid.models.mappers;

import com.example.teste_backend_java_tgid.models.dtos.inputs.cliente.CreateClienteDTO;
import com.example.teste_backend_java_tgid.models.dtos.outputs.cliente.ClienteOutputDTO;
import com.example.teste_backend_java_tgid.models.entities.Cliente;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel  = "spring")
public interface ClienteMapper {
    ClienteMapper INSTANCE = Mappers.getMapper( ClienteMapper.class );

    Cliente dtoToCliente(CreateClienteDTO dto);

    @Mapping(source = "transacoes", target = "transacoes")
    ClienteOutputDTO entitytoDTO(Cliente entity);

}
