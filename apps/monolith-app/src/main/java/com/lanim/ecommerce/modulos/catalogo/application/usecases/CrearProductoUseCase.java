package com.lanim.ecommerce.modulos.catalogo.application.usecases;

import com.lanim.ecommerce.common.domain.Money;
import com.lanim.ecommerce.modulos.catalogo.application.dtos.commands.CrearProductoCommand;
import com.lanim.ecommerce.modulos.catalogo.application.dtos.out.CrearProductoOutputDto;
import com.lanim.ecommerce.modulos.catalogo.application.ports.in.CrearProductoInputPort;
import com.lanim.ecommerce.modulos.catalogo.application.ports.out.ProductoPort;
import com.lanim.ecommerce.modulos.catalogo.domain.model.Producto;
import com.lanim.ecommerce.modulos.catalogo.infrastructure.mappers.ProductoMapper;
import org.mapstruct.Mapper;
import org.springframework.boot.context.properties.PropertyMapper;
import org.springframework.stereotype.Service;

@Service
public class CrearProductoUseCase implements CrearProductoInputPort {
    private final ProductoPort productoPort;
    private final ProductoMapper mapper;

    public CrearProductoUseCase(ProductoPort productoPort, ProductoMapper mapper) {
        this.productoPort = productoPort;
        this.mapper = mapper;
    }

    @Override
    public CrearProductoOutputDto execute(CrearProductoCommand command) {

        if(command == null){
            throw new IllegalArgumentException("el comando no puede ser nulo");
        }
        if(productoPort.existsByName(command.nombre())){
            throw new IllegalArgumentException("este nombre ya existe en otro producto");
        }
        Money commandPrice = new Money(command.amount(), command.currency());
        Producto nuevoProducto = Producto.createProduct(command.nombre(), commandPrice, command.stock(), command.descripcion());
        Producto savedProduct = productoPort.addProduct(nuevoProducto);
        return mapper.domainToOutputDto(savedProduct);
    }
}
