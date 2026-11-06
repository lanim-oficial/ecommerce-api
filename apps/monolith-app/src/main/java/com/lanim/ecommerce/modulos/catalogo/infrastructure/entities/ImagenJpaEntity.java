package com.lanim.ecommerce.modulos.catalogo.infrastructure.entities;

import jakarta.persistence.*;

import java.util.UUID;

@Entity(name = "imagenes")
@Table(name = "imagenes")
public class ImagenJpaEntity {
    @Id
    @Column(
            name = "id_imagen",
            nullable = false
    )
    private UUID id;

    @Column(
            name = "url",
            nullable = false
    )
    private String url;

    @Column(
            name = "principal"
    )
    private boolean principal;

    @Column(
            name = "texto_alternativo",
            nullable = false
    )
    private String altText;

    @ManyToOne(
            fetch = FetchType.LAZY,
            optional = false
    )
    @JoinColumn(name = "product_id", nullable = false)
    private ProductoJpaEntity producto;

    public ImagenJpaEntity(UUID id, String url, boolean principal, String altText) {
        this.id = id;
        this.url = url;
        this.principal = principal;
        this.altText = altText;
    }

    public ImagenJpaEntity() {}

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public boolean isPrincipal() {
        return principal;
    }

    public void setPrincipal(boolean principal) {
        this.principal = principal;
    }

    public String getAltText() {
        return altText;
    }

    public void setAltText(String altText) {
        this.altText = altText;
    }

    public ProductoJpaEntity getProducto() {
        return producto;
    }

    public void setProducto(ProductoJpaEntity producto) {
        this.producto = producto;
    }
}
