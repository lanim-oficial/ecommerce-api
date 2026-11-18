package com.lanim.ecommerce.modulos.catalogo.domain.model;

import com.lanim.ecommerce.common.domain.Discount;
import com.lanim.ecommerce.common.domain.Money;

import java.math.BigDecimal;
import java.util.*;

public class  Producto {
    private UUID id;
    private String nombre;
    private List<Imagen> imagenes;
    private Money precio;
    private Long stock;
    private String descripcion;
    private Discount descuento;
    private boolean publicado;

    private Producto(UUID id, String nombre, List<Imagen> imagenes, Money precio, Long stock, String descripcion, Discount descuento, boolean publicado) {
        this.id = id;
        this.nombre = nombre;
        this.imagenes = imagenes;
        this.precio = precio;
        this.stock = stock;
        this.descripcion = descripcion;
        this.descuento = descuento;
        this.publicado = publicado;
    }

    public static Producto createProduct(String nombre, Money precio, Long stock, String descripcion) {
        if (nombre == null || nombre.isBlank()){
            throw new IllegalArgumentException("no puede ser un string vacio ni nulo");
        }

        if (stock < 0){
            throw new IllegalArgumentException("el stock no puede ser negativo");
        }

        return new Producto(
                UUID.randomUUID(),
                nombre,
                new ArrayList<Imagen>(),
                Objects.requireNonNull(precio, "El precio no puede ser nulo"),
                stock,
                Objects.requireNonNull(descripcion, "La descripción no puede ser nula"),
                new Discount(BigDecimal.ZERO),
                false
        );
    }

    public static Producto retrieveProduct(
            UUID id,
            String name,
            List<Imagen> images,
            Money price,
            Long stock,
            String description,
            Discount discount,
            boolean shared
    ) {
        return new Producto(
                id,
                name,
                images,
                price,
                stock,
                description,
                discount,
                shared
        );
    }

    public Long agregarStock (Long cantidad){
        if(cantidad <= 0){
            throw new IllegalArgumentException("la cantidad no puede ser menor a cero");
        }
        stock += cantidad;
        return stock;
    }

    public void reducirStock (Long cantidad){
        if(cantidad <= 0){
            throw new IllegalArgumentException("la cantidad no puede ser menor a cero");
        }
        if(cantidad > stock){
            throw new IllegalArgumentException("No hay suficiente stock");
        }
        stock -= cantidad;
    }

    public Producto actualizarPrecio (Money monto){
        if(monto == null){
            throw new IllegalArgumentException("no puede ser nulo");
        }
        precio = monto;
        return this;
    }

    public Producto actualizarNombre (String nuevoNombre){
        nombre = Objects.requireNonNull(nuevoNombre, "El nuevo nombre no puede ser nulo");
        if(nuevoNombre.isBlank()){
            throw new IllegalArgumentException("no puede estar vacio");
        }
        return this;
    }

    public Producto actualizarDescripcion (String nuevaDescripcion){
        if(nuevaDescripcion == null){
            throw new IllegalArgumentException("descripcion no puede ser nula");
        }
        descripcion = nuevaDescripcion;
        return this;
    }

    public Money precioDescuento(){
        return descuento.applyDiscount(precio);
    }

    public boolean disponibilidad(){
        return stock > 0;
    }

    public Money estblecerDescuento(Discount nuevoDescuento){
        if(nuevoDescuento == null){
            throw new IllegalArgumentException("no puede ser nulo");
        }
        descuento = nuevoDescuento;
        return precioDescuento();
    }

    public void publicarProducto() {
        if (publicado) {
            throw new IllegalArgumentException("El producto ya está publicado");
        }
        if (imagenes.isEmpty()) {
            throw new IllegalArgumentException("El producto no puede ser publicado sin imágenes");
        }
        int counter = 0;
        for (Imagen i : imagenes) {
            if (i.isPrincipal()) {
                counter++;
            }
        }
        if (counter != 1) throw new IllegalArgumentException("El producto debe tener una imagen principal");
        publicado = true;
    }

    public void privatizarProducto() {
        if (!publicado) {
            throw new IllegalArgumentException("El producto ya es privado");
        }
        publicado = false;
    }

    public void agregarImagen(Imagen imagen) {
        if (imagen == null) throw new IllegalArgumentException("No se puede agregar una imagen nula");
        imagenes.add(imagen);
    }

    public void hacerImagenPrincipal(UUID idImagen) {
        Objects.requireNonNull(idImagen, "El id no puede ser nulo");
        int counter = 0;
        for(Imagen i : imagenes){
            if (i.getId().equals(idImagen)) {
                counter++;
            }
        }
        if (counter == 0) throw new IllegalArgumentException("No se encontro la imagen");
        for (Imagen i : imagenes) {
            if (i.isPrincipal()) {
                i.eliminarEstadoPrincipal();
            }
            if (i.getId().equals(idImagen)) {
                i.hacerPrincipal();
            }
        }
    }

    public UUID getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public List<Imagen> getImagenes() {
        return imagenes;
    }

    public Long getStock() {
        return stock;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public Discount getDescuento() {
        return descuento;
    }

    public boolean isPublicado() {
        return publicado;
    }

    public Money getPrecio() {
        return precio;
    }
}