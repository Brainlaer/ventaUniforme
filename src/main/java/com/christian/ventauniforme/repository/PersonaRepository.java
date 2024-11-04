package com.christian.ventauniforme.repository;

import com.christian.ventauniforme.model.Persona;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonaRepository extends JpaRepository<Persona, Long> {

    Persona findByCorreo(String correo);
}
