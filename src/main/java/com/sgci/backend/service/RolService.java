package com.sgci.backend.service;

import com.sgci.backend.entity.Rol;
import com.sgci.backend.repository.RolRepository;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RolService {

    private final RolRepository rolRepository;

    // Inyeccion de dependencias
    public RolService(RolRepository rolRepository) {
        this.rolRepository = rolRepository;
    }

    // Consultar todos los roles
    public List<Rol> listarRoles() {

        return rolRepository.findAll();

    }
}