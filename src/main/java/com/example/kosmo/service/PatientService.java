package com.example.kosmo.service;

import com.example.kosmo.model.Patient;
import com.example.kosmo.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class PatientService {
    @Autowired
    private PacienteRepository pacienteRepository;

    public List<Patient> getPatients() {
        return pacienteRepository.findAll();
    }

    public boolean addPatient(Patient patient) {
        LocalDate todayDate = LocalDate.now();
        LocalDate patientDateBirth = patient.getDateBirth();
        LocalDate minDate = LocalDate.now().minusYears(120);
        if(patientDateBirth.isAfter(todayDate) || patientDateBirth.isBefore(minDate))
            return false;
        pacienteRepository.save(patient);
        return true;
    }
}
