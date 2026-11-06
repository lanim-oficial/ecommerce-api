package com.lanim.ecommerce.modulos.catalogo.infrastructure.repositories;

import com.lanim.ecommerce.modulos.catalogo.infrastructure.entities.ProductoJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProductRepository extends JpaRepository<ProductoJpaEntity, UUID> {
    boolean existsByNombreIgnoreCase(String nombre);
}
