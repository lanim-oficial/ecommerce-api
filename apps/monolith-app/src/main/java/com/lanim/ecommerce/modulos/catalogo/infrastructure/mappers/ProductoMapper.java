package com.lanim.ecommerce.modulos.catalogo.infrastructure.mappers;

import com.lanim.ecommerce.modulos.catalogo.application.dtos.commands.CrearProductoCommand;
import com.lanim.ecommerce.modulos.catalogo.application.dtos.out.CrearProductoOutputDto;
import com.lanim.ecommerce.modulos.catalogo.infrastructure.dtos.requests.CrearProductoRequest;
import com.lanim.ecommerce.modulos.catalogo.infrastructure.dtos.responses.CrearProductoResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ProductoMapper {
    CrearProductoCommand requestToCommand(CrearProductoRequest request);
    CrearProductoResponse outputDtoToResponse(CrearProductoOutputDto response);
}
