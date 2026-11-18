package com.sgci.backend.service;

import com.sgci.backend.entity.Categoria;
import com.sgci.backend.repository.CategoriaRepository;

import org.springframework.stereotype.Service;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CategoriaService {

    private final CategoriaRepository categoriaRepository;

    public CategoriaService(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    // CONSULTAR TODAS LAS CATEGORIAS
    public List<Categoria> listarCategorias() {
        return categoriaRepository.findAll();
    }

    // CONSULTAR CATEGORIA POR ID
    public Categoria buscarPorId(Long id) {

        return categoriaRepository.findById(id)
                .orElseThrow(() ->
                    new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Categoria no encontrada"
                    )
                );
    }

    // REGISTRAR CATEGORIA
    @Transactional
    public Categoria crearCategoria(Categoria categoria) {

        if (categoriaRepository.existsByNombreIgnoreCase(categoria.getNombre())) {
            throw new ResponseStatusException(
                HttpStatus.CONFLICT,
                "La categoria ya existe"
            );
        }

        categoria.setActiva(true);

        return categoriaRepository.save(categoria);
    }

    // ACTUALIZAR CATEGORIA
    @Transactional
    public Categoria actualizarCategoria(Long id, Categoria datos) {

        Categoria existente = buscarPorId(id);

        if (categoriaRepository.existsByNombreIgnoreCaseAndIdNot(
                datos.getNombre(), id)) {

            throw new ResponseStatusException(
                HttpStatus.CONFLICT,
                "Ya existe otra categoria con ese nombre"
            );
        }

        existente.setNombre(datos.getNombre());
        existente.setDescripcion(datos.getDescripcion());

        return categoriaRepository.save(existente);
    }

    // DESACTIVAR CATEGORIA
    @Transactional
    public void desactivarCategoria(Long id) {

        Categoria existente = buscarPorId(id);

        existente.setActiva(false);

        categoriaRepository.save(existente);
    }
}