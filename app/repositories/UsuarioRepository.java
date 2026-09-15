package com.saludmza.app.repositories;

import com.saludmza.app.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository // Define el componente de acceso a datos
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    // Método derivado por convención de nombre ("findByEmail") para buscar un usuario por su correo
    Optional<Usuario> findByEmail(String email);
}