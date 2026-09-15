package com.saludmza.app.repositories;

import com.saludmza.app.models.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Long> {
    // Método para recuperar un paciente a partir de su número de DNI
    Optional<Paciente> findByDni(String dni);
}