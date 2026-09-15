package com.saludmza.app.repositories;

import com.saludmza.app.models.Administrador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdministradorRepository extends JpaRepository<Administrador, Long> {
    // Búsqueda de administrador por legajo
    Optional<Administrador> findByLegajo(String legajo);

    // Búsqueda de administrador por usuario de red
    Optional<Administrador> findByUsuarioRed(String usuarioRed);
}