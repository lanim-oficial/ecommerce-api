package com.lanim.ecommerce.modulos.catalogo.infrastructure.mappers;

import com.lanim.ecommerce.common.domain.Discount;
import com.lanim.ecommerce.common.domain.Money;
import com.lanim.ecommerce.common.infrastructure.persistence.embeddable.DiscountJpaEmbeddable;
import com.lanim.ecommerce.common.infrastructure.persistence.embeddable.MoneyJpaEmbeddable;
import com.lanim.ecommerce.modulos.catalogo.application.dtos.commands.CrearProductoCommand;
import com.lanim.ecommerce.modulos.catalogo.application.dtos.out.CrearProductoOutputDto;
import com.lanim.ecommerce.modulos.catalogo.domain.model.Imagen;
import com.lanim.ecommerce.modulos.catalogo.domain.model.Producto;
import com.lanim.ecommerce.modulos.catalogo.infrastructure.dtos.requests.CrearProductoRequest;
import com.lanim.ecommerce.modulos.catalogo.infrastructure.dtos.responses.CrearProductoResponse;
import com.lanim.ecommerce.modulos.catalogo.infrastructure.entities.ImagenJpaEntity;
import com.lanim.ecommerce.modulos.catalogo.infrastructure.entities.ProductoJpaEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ObjectFactory;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductoMapper {
    CrearProductoCommand requestToCommand(CrearProductoRequest request);
    CrearProductoResponse outputDtoToResponse(CrearProductoOutputDto response);
    ProductoJpaEntity domainToJpaEntity(Producto producto);
    @Mapping(source = "precio.amount", target = "amount")
    @Mapping(source = "precio.currency", target = "currency")
    CrearProductoOutputDto domainToOutputDto(Producto producto);
    CrearProductoOutputDto jpaEntityToOutputDto(ProductoJpaEntity productoJpaEntity);
    @Mapping(source = "currency" , target = "currencyCode")
    MoneyJpaEmbeddable domainMoneyToJpaEntity(Money money);
    @Mapping(source = "currencyCode", target = "currency")
    Money jpaMoneyEntityToDomain (MoneyJpaEmbeddable moneyJpaEmbeddable);
    @Mapping(source = "value", target = "descuento")
    DiscountJpaEmbeddable domainDiscountToJpaEntity(Discount discount);
    @Mapping(source = "descuento", target = "value")
    Discount jpaEntityDiscountToDomain (DiscountJpaEmbeddable discountJpaEmbeddable);
    ImagenJpaEntity domainImageToJpaEntity(Imagen imagen);
    Imagen jpaImageEntityToDomain(ImagenJpaEntity imagenJpaEntity);
    @ObjectFactory
    default Producto jpaEntityToDomain(ProductoJpaEntity productoJpaEntity) {

        List<Imagen> images = productoJpaEntity.getImagenes().stream()
                .map(this::jpaImageEntityToDomain)
                .toList();

        Money price = this.jpaMoneyEntityToDomain(productoJpaEntity.getPrecio());

        Discount discount = this.jpaEntityDiscountToDomain(productoJpaEntity.getDescuento());

        return Producto.retrieveProduct(
                productoJpaEntity.getId(),
                productoJpaEntity.getNombre(),
                images,
                price,
                productoJpaEntity.getStock(),
                productoJpaEntity.getDescripcion(),
                discount,
                productoJpaEntity.getPublicado()
        );
    }
}
