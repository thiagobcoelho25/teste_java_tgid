package com.example.teste_backend_java_tgid.models.mappers;

import com.example.teste_backend_java_tgid.models.dtos.outputs.taxa.TaxaOutputDTO;
import com.example.teste_backend_java_tgid.models.entities.TipoTaxa;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel  = "spring")
public interface TaxaMapper {

    TaxaMapper INSTANCE = Mappers.getMapper( TaxaMapper.class );

     TaxaOutputDTO entitytoDTO(TipoTaxa entity);

}
