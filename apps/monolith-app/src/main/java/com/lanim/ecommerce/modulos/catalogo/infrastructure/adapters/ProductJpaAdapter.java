package com.lanim.ecommerce.modulos.catalogo.infrastructure.adapters;

import com.lanim.ecommerce.modulos.catalogo.application.dtos.out.CrearProductoOutputDto;
import com.lanim.ecommerce.modulos.catalogo.application.ports.out.ProductoPort;
import com.lanim.ecommerce.modulos.catalogo.domain.model.Producto;
import com.lanim.ecommerce.modulos.catalogo.infrastructure.entities.ProductoJpaEntity;
import com.lanim.ecommerce.modulos.catalogo.infrastructure.mappers.ProductoMapper;
import com.lanim.ecommerce.modulos.catalogo.infrastructure.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ProductJpaAdapter implements ProductoPort {

    private final ProductRepository productRepository;
    private final ProductoMapper mapper;

    public ProductJpaAdapter(ProductRepository productRepository, ProductoMapper mapper) {
        this.productRepository = productRepository;
        this.mapper = mapper;
    }

    @Override
    public Producto addProduct(Producto producto) {
        if (producto == null) throw new IllegalArgumentException("El producto no puede ser nulo");
        ProductoJpaEntity jpaProduct = mapper.domainToJpaEntity(producto);
        var savedProduct = productRepository.save(jpaProduct);
        return mapper.jpaEntityToDomain(savedProduct);
    }

    @Override
    public boolean existsByName(String nombre) {
        return productRepository.existsByNombreIgnoreCase(nombre);
    }
}
