package com.example.kosmo.service;

import com.example.kosmo.model.Specialty;
import com.example.kosmo.repository.SpecialtyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SpecialtyService {

    @Autowired
    private SpecialtyRepository specialtyRepository;

    public List<Specialty> getSpecialties(){
        return specialtyRepository.findAll();
    }

    public void addSpecialty(Specialty specialty){
        specialtyRepository.save(specialty);
    }
}
