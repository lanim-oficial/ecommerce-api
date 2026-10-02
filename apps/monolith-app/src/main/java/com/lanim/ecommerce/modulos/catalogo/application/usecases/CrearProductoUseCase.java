package com.lanim.ecommerce.modulos.catalogo.application.usecases;

import com.lanim.ecommerce.common.valueobjects.Money;
import com.lanim.ecommerce.modulos.catalogo.application.dtos.commands.CrearProductoCommand;
import com.lanim.ecommerce.modulos.catalogo.application.ports.in.CrearProductoInputPort;
import com.lanim.ecommerce.modulos.catalogo.application.ports.out.ProductoPort;
import com.lanim.ecommerce.modulos.catalogo.domain.model.Producto;

public class CrearProductoUseCase implements CrearProductoInputPort {
    private final ProductoPort productoPort;

    public CrearProductoUseCase(ProductoPort productoPort) {
        this.productoPort = productoPort;
    }

    @Override
    public Producto execute(CrearProductoCommand command) {
        if(command == null){
            throw new IllegalArgumentException("el comando no puede ser nulo");
        }
        if(productoPort.existsByName(command.nombre())){
            throw new IllegalArgumentException("este nombre ya existe en otro producto");
        }
        Money commandPrice = new Money(command.amount(), command.currency());
        Producto nuevoProducto = new Producto(command.nombre(), commandPrice, command.stock(), command.descripcion());

        return productoPort.addProduct(nuevoProducto);
    }
}
