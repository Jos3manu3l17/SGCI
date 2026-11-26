package com.sgci.backend.repository;

import com.sgci.backend.entity.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import java.util.List;

public interface ProductoRepository extends JpaRepository<Producto, Long> {
    Optional<Producto> findByCodigo(String codigo);
    List<Producto> findByStockActualLessThanEqual(Integer stockMinimo);
}
