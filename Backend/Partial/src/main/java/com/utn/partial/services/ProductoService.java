package com.utn.partial.services;

import com.utn.partial.entities.Producto;
import com.utn.partial.repositories.ProductoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service para gestionar la lógica de negocio de los productos.
 */
@Service
@RequiredArgsConstructor
public class ProductoService {

    private final ProductoRepository productoRepository;

    /**
     * Obtiene todos los productos.
     * @return Lista de todos los productos
     */
    public List<Producto> getAllProductos() {
        return productoRepository.findAll();
    }

    /**
     * Obtiene un producto por su ID.
     * @param id ID del producto
     * @return Producto encontrado o vacío
     */
    public Optional<Producto> getProductoById(Long id) {
        return productoRepository.findById(id);
    }

    /**
     * Crea un nuevo producto.
     * @param producto Producto a crear
     * @return Producto creado
     */
    public Producto createProducto(Producto producto) {
        return productoRepository.save(producto);
    }

    /**
     * Actualiza un producto existente.
     * @param id ID del producto a actualizar
     * @param producto Datos del producto actualizado
     * @return Producto actualizado o vacío si no existe
     */
    public Optional<Producto> updateProducto(Long id, Producto producto) {
        return productoRepository.findById(id)
                .map(existingProducto -> {
                    existingProducto.setNombre(producto.getNombre());
                    existingProducto.setDescripcion(producto.getDescripcion());
                    existingProducto.setPrecio(producto.getPrecio());
                    existingProducto.setStock(producto.getStock());
                    return productoRepository.save(existingProducto);
                });
    }

    /**
     * Elimina un producto por su ID.
     * @param id ID del producto a eliminar
     * @return true si se eliminó, false si no existía
     */
    public boolean deleteProducto(Long id) {
        if (productoRepository.existsById(id)) {
            productoRepository.deleteById(id);
            return true;
        }
        return false;
    }
}