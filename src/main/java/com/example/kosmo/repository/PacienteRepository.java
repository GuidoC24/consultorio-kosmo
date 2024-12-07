package com.example.kosmo.repository;

import com.example.kosmo.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PacienteRepository extends JpaRepository<Patient, Long> {
}
