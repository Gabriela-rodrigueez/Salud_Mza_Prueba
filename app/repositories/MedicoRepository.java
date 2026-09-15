package com.saludmza.app.repositories;

import com.saludmza.app.models.Medico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MedicoRepository extends JpaRepository<Medico, Long> {
    // Busca a un médico por su matrícula profesional
    Optional<Medico> findByMatriculaProfesional(String matriculaProfesional);
}
