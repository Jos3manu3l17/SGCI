package com.sgci.backend.controller;

import com.sgci.backend.entity.Categoria;
import com.sgci.backend.dto.CategoriaRequest;
import com.sgci.backend.service.CategoriaService;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/api/categorias")
public class CategoriaController {

    private final CategoriaService categoriaService;

    public CategoriaController(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    // GET: CONSULTAR TODAS
    @GetMapping
    public ResponseEntity<List<Categoria>> listarCategorias() {

        return ResponseEntity.ok(
            categoriaService.listarCategorias()
        );
    }

    // GET: CONSULTAR POR ID
    @GetMapping("/{id}")
    public ResponseEntity<Categoria> buscarPorId(@PathVariable Long id) {

        return ResponseEntity.ok(
            categoriaService.buscarPorId(id)
        );
    }

    // POST: REGISTRAR
    @PostMapping
    public ResponseEntity<Categoria> crearCategoria(
            @Valid @RequestBody CategoriaRequest request) {

        Categoria categoria = new Categoria();

        categoria.setNombre(request.getNombre());
        categoria.setDescripcion(request.getDescripcion());

        Categoria nueva = categoriaService.crearCategoria(categoria);

        return ResponseEntity.status(HttpStatus.CREATED).body(nueva);
    }

    // PUT: ACTUALIZAR
    @PutMapping("/{id}")
    public ResponseEntity<Categoria> actualizarCategoria(
            @PathVariable Long id,
            @Valid @RequestBody CategoriaRequest request) {

        Categoria datos = new Categoria();

        datos.setNombre(request.getNombre());
        datos.setDescripcion(request.getDescripcion());

        Categoria actualizada =
                categoriaService.actualizarCategoria(id, datos);

        return ResponseEntity.ok(actualizada);
    }

    // DELETE: DESACTIVAR
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> desactivarCategoria(@PathVariable Long id) {

        categoriaService.desactivarCategoria(id);

        return ResponseEntity.noContent().build();
    }
}