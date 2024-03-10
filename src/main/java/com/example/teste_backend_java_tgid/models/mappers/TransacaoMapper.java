package com.example.teste_backend_java_tgid.models.mappers;

import com.example.teste_backend_java_tgid.models.dtos.outputs.transacao.TransacaoOutputDTO;
import com.example.teste_backend_java_tgid.models.entities.Transacao;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel  = "spring")
public interface TransacaoMapper {

    TransacaoMapper INSTANCE = Mappers.getMapper( TransacaoMapper.class );

    List<TransacaoOutputDTO> ListentityToDTO(List<Transacao> entity);
    TransacaoOutputDTO entityToDTO(Transacao entity);
}
