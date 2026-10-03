package com.lanim.ecommerce.modulos.catalogo.infrastructure.web;

import com.lanim.ecommerce.modulos.catalogo.application.dtos.commands.CrearProductoCommand;
import com.lanim.ecommerce.modulos.catalogo.application.dtos.out.CrearProductoOutputDto;
import com.lanim.ecommerce.modulos.catalogo.application.ports.in.CrearProductoInputPort;
import com.lanim.ecommerce.modulos.catalogo.infrastructure.dtos.requests.CrearProductoRequest;
import com.lanim.ecommerce.modulos.catalogo.infrastructure.dtos.responses.CrearProductoResponse;
import com.lanim.ecommerce.modulos.catalogo.infrastructure.mappers.ProductoMapper;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/v1/lanim/catalogo/productos")
public class ProtuctoController {
    private final CrearProductoInputPort crearProductoInputPort;
    private final ProductoMapper mapper;

    public ProtuctoController(CrearProductoInputPort crearProductoInputPort, ProductoMapper mapper) {
        this.crearProductoInputPort = crearProductoInputPort;
        this.mapper = mapper;
    }

    @PostMapping
    public ResponseEntity<CrearProductoResponse> crearProducto(@RequestBody @Valid CrearProductoRequest request) {
        CrearProductoCommand command = mapper.requestToCommand(request);
        CrearProductoOutputDto productoCreado = crearProductoInputPort.execute(command);
        CrearProductoResponse response= mapper.outputDtoToResponse(productoCreado);

        URI ubicacion = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(productoCreado.id())
                .toUri();

        return ResponseEntity.created(ubicacion).body(response);
    }
}
