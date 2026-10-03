package com.lanim.ecommerce.modulos.catalogo.application.ports.out;

import com.lanim.ecommerce.modulos.catalogo.application.dtos.out.CrearProductoOutputDto;
import com.lanim.ecommerce.modulos.catalogo.domain.model.Producto;

public interface ProductoPort {
    CrearProductoOutputDto addProduct(Producto producto);
    boolean existsByName(String nombre);
}
