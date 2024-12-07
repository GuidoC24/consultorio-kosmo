package com.example.kosmo.service;


import com.example.kosmo.model.Appointment;
import com.example.kosmo.model.Doctor;
import com.example.kosmo.model.Office;
import com.example.kosmo.repository.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AppointmentService {
    @Autowired
    private AppointmentRepository appointmentRepository;

    public void addAppointment(Appointment appointment) {
        appointmentRepository.save(appointment);
    }

    public List<Appointment> getGeneralAppointments(){
        return  appointmentRepository.findAll();
    }

    public List<Appointment> findValidAppointments(Doctor doctor, Office office, LocalDateTime start, LocalDateTime end) {
        return appointmentRepository.findByDoctorAndOfficeAndAppointmentDateBetween(doctor, office, start, end);
    }

}
