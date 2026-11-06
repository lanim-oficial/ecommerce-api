package com.lanim.ecommerce.modulos.catalogo.infrastructure.entities;

import com.lanim.ecommerce.common.infrastructure.persistence.embeddable.DiscountJpaEmbeddable;
import com.lanim.ecommerce.common.infrastructure.persistence.embeddable.MoneyJpaEmbeddable;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity(name = "producto")
@Table(name = "producto")
public class ProductoJpaEntity {
    @Id
    @Column(
            name = "producto_id",
            nullable = false
    )
    private UUID id;

    @Column(
            name = "nombre",
            nullable = false,
            length = 160
    )
    private String nombre;

    @OneToMany(
            mappedBy = "producto",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<ImagenJpaEntity> imagenes = new ArrayList<>();

    @Embedded
    private MoneyJpaEmbeddable precio;

    @Column(
            name = "stock",
            nullable = false,
            columnDefinition = "INT"
    )
    private long stock;

    @Column(
            name = "descripcion",
            columnDefinition =  "TEXT"
    )
    private String descripcion;

    @Embedded
    private DiscountJpaEmbeddable descuento;

    @Column(
            name = "publicado",
            nullable = false
    )
    private boolean publicado;

    public ProductoJpaEntity(UUID id, String nombre, List<ImagenJpaEntity> imagenes, MoneyJpaEmbeddable precio, long stock, String descripcion, DiscountJpaEmbeddable descuento, boolean publicado) {
        this.id = id;
        this.nombre = nombre;
        this.imagenes = imagenes;
        this.precio = precio;
        this.stock = stock;
        this.descripcion = descripcion;
        this.descuento = descuento;
        this.publicado = publicado;
    }

    public ProductoJpaEntity() {}

    public void addImage(ImagenJpaEntity image) {
        if (image == null) throw new IllegalArgumentException("La imagen no puede ser nula");
        imagenes.add(image);
        image.setProducto(this);
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<ImagenJpaEntity> getImagenes() {
        return imagenes;
    }

    public void setImagenes(List<ImagenJpaEntity> imagenes) {
        this.imagenes = imagenes;
    }

    public MoneyJpaEmbeddable getPrecio() {
        return precio;
    }

    public void setPrecio(MoneyJpaEmbeddable precio) {
        this.precio = precio;
    }

    public long getStock() {
        return stock;
    }

    public void setStock(long stock) {
        this.stock = stock;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public DiscountJpaEmbeddable getDescuento() {
        return descuento;
    }

    public void setDescuento(DiscountJpaEmbeddable descuento) {
        this.descuento = descuento;
    }

    public boolean getPublicado() {
        return publicado;
    }

    public void setPublicado(boolean publicado) {
        this.publicado = publicado;
    }
}