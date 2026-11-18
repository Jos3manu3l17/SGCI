package com.sgci.backend.controller;

import com.sgci.backend.entity.Rol;
import com.sgci.backend.service.RolService;

import org.springframework.web.bind.annotation.*;

import org.springframework.http.ResponseEntity;

import java.util.List;

@RestController
@RequestMapping("/api/roles")
public class RolController {

    private final RolService rolService;

    // Inyeccion del servicio
    public RolController(RolService rolService) {
        this.rolService = rolService;
    }

    // Consultar todos los roles
    @GetMapping
    public ResponseEntity<List<Rol>> listarRoles() {

        return ResponseEntity.ok(rolService.listarRoles());

    }
}