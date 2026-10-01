package com.lanim.ecommerce.modulos.catalogo.domain.model;

import java.util.UUID;

public class Imagen {
    private UUID id;
    private String url;
    private boolean principal;
    private String altText;

    public Imagen(String url, String altText) {
        this.id = UUID.randomUUID();
        if (url == null || url.isBlank()) throw new IllegalArgumentException("La url no puede estar vacía o ser nula");
        this.url = url;
        this.principal = false;
        if (altText == null || altText.isBlank()) throw new IllegalArgumentException("El texto alternativo no puede estar vacío o ser nulo");
        this.altText = altText;
    }

    public void cambiarUrl(String nuevaUrl) {
        if (nuevaUrl == null || nuevaUrl.isBlank()) throw new IllegalArgumentException("La nueva url no puede estar vacía o ser nula");
        this.url = nuevaUrl;
    }

    void hacerPrincipal() {
        if (principal) throw new IllegalArgumentException("La imagen ya es principal");
        principal = true;
    }

    void eliminarEstadoPrincipal() {
        if (!principal) throw new IllegalArgumentException("La imagen no es principal");
        principal = false;
    }

    public void cambiarTextoAlternativo(String textoAlternativo) {
        if (textoAlternativo == null || textoAlternativo.isBlank()) throw new IllegalArgumentException("El texto alternativo no puede estar vacío o ser nulo");
        this.altText = textoAlternativo;
    }

    public boolean isPrincipal() {
        return principal;
    }

    public UUID getId() {
        return id;
    }
}