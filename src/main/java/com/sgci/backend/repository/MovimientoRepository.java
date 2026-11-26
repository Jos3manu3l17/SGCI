package com.sgci.backend.repository;

import com.sgci.backend.entity.Movimiento;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface MovimientoRepository extends JpaRepository<Movimiento, Long> {
    List<Movimiento> findByProductoId(Long productoId);
}
