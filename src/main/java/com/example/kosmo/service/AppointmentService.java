package com.example.kosmo.service;


import com.example.kosmo.model.Appointment;
import com.example.kosmo.repository.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public List<Appointment> getAppointments() {
        return appointmentRepository.findAll().stream().sorted(Comparator.comparing(Appointment::getAppointmentDate)).collect(Collectors.toList());
    }
}
