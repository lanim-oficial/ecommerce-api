package com.lanim.ecommerce.modulos.catalogo.application.ports.in;

import com.lanim.ecommerce.modulos.catalogo.application.dtos.commands.CrearProductoCommand;
import com.lanim.ecommerce.modulos.catalogo.application.dtos.out.CrearProductoOutputDto;

public interface CrearProductoInputPort {
    CrearProductoOutputDto execute(CrearProductoCommand command);
}