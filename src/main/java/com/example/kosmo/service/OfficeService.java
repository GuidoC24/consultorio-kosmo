package com.example.kosmo.service;

import com.example.kosmo.model.Office;
import com.example.kosmo.repository.OfficeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OfficeService {

    @Autowired
    private OfficeRepository officeRepository;

    public List<Office> getOffices(){
        return officeRepository.findAll();
    }

    public void addOffice(Office office){
        officeRepository.save(office);
    }

    public Optional<Office> findOfficeById(long id){
        return officeRepository.findById(id);
    }
}
