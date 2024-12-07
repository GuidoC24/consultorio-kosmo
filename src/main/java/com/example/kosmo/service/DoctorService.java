package com.example.kosmo.service;

import com.example.kosmo.model.Doctor;
import com.example.kosmo.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorService {
    @Autowired
    private DoctorRepository doctorRepository;

    public List<Doctor> getDoctors() {
        return doctorRepository.findAll();
    }

    public void addDoctor(Doctor doctor) {
        doctorRepository.save(doctor);
    }
}

