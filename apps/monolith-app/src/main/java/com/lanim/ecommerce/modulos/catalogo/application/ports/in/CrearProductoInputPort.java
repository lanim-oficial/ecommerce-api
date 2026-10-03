package com.lanim.ecommerce.modulos.catalogo.application.ports.in;

import com.lanim.ecommerce.modulos.catalogo.application.dtos.commands.CrearProductoCommand;
import com.lanim.ecommerce.modulos.catalogo.application.dtos.out.CrearProductoOutputDto;
import com.lanim.ecommerce.modulos.catalogo.domain.model.Producto;

public interface CrearProductoInputPort {
    CrearProductoOutputDto execute(CrearProductoCommand command);
}