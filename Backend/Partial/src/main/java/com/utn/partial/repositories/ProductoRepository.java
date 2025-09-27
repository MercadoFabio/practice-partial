package com.utn.partial.repositories;

import com.utn.partial.entities.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository para gestionar las operaciones CRUD de Producto.
 */
@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {
    // Spring Data JPA proporciona automáticamente los métodos CRUD básicos
    // como save(), findAll(), findById(), deleteById(), etc.
}