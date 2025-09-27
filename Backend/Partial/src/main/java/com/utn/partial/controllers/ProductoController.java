package com.utn.partial.controllers;

import com.utn.partial.entities.Producto;
import com.utn.partial.services.ProductoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller REST para gestionar los productos.
 */
@RestController
@RequestMapping("/api/productos")
@CrossOrigin(origins = "*") // Permitir CORS para el frontend
@RequiredArgsConstructor
public class ProductoController {

    private final ProductoService productoService;

    /**
     * Obtiene todos los productos.
     * GET /api/productos
     */
    @GetMapping
    public ResponseEntity<List<Producto>> getAllProductos() {
        List<Producto> productos = productoService.getAllProductos();
        return ResponseEntity.ok(productos);
    }

    /**
     * Obtiene un producto por ID.
     * GET /api/productos/{id}
     */
    @GetMapping("/{id}")
    public ResponseEntity<Producto> getProductoById(@PathVariable Long id) {
        return productoService.getProductoById(id)
                .map(producto -> ResponseEntity.ok(producto))
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Crea un nuevo producto.
     * POST /api/productos
     */
    @PostMapping
    public ResponseEntity<Producto> createProducto(@RequestBody Producto producto) {
        try {
            Producto nuevoProducto = productoService.createProducto(producto);
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevoProducto);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    /**
     * Actualiza un producto existente.
     * PUT /api/productos/{id}
     */
    @PutMapping("/{id}")
    public ResponseEntity<Producto> updateProducto(@PathVariable Long id, @RequestBody Producto producto) {
        return productoService.updateProducto(id, producto)
                .map(productoActualizado -> ResponseEntity.ok(productoActualizado))
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Elimina un producto.
     * DELETE /api/productos/{id}
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProducto(@PathVariable Long id) {
        if (productoService.deleteProducto(id)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}