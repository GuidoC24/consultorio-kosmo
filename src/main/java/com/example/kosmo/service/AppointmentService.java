package com.example.kosmo.service;


import com.example.kosmo.model.Appointment;
import com.example.kosmo.model.Doctor;
import com.example.kosmo.model.Office;
import com.example.kosmo.repository.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AppointmentService {
    @Autowired
    private AppointmentRepository appointmentRepository;

    public boolean addAppointment(Appointment appointment) {
        List<Appointment> appointmentsOffice = appointmentRepository.findByOfficeAndAppointmentDateBetween(appointment.getOffice(), appointment.getAppointmentDate().minusMinutes(1), appointment.getAppointmentDate().plusMinutes(1));
        if (!appointmentsOffice.isEmpty()) {
            return false;
        }
        List<Appointment> appointmentsDoctor = appointmentRepository.findByDoctorAndAppointmentDateBetween(appointment.getDoctor(), appointment.getAppointmentDate().minusMinutes(1), appointment.getAppointmentDate().plusMinutes(1));
        if (!appointmentsDoctor.isEmpty()) {
            return false;
        }
        List<Appointment> appointmentsPatient = appointmentRepository.findByDoctorAndAppointmentDateBetween(appointment.getDoctor(), appointment.getAppointmentDate().minusHours(2), appointment.getAppointmentDate().plusHours(2));
        for (Appointment a : appointmentsPatient) {
            if (a.getPatient().getName().equals(appointment.getPatient().getName())) {
                return false;
            }
        }
        LocalDateTime startDay = appointment.getAppointmentDate().toLocalDate().atStartOfDay();
        LocalDateTime endDay = startDay.plusDays(1);
        List<Appointment> appointmentsDay = appointmentRepository.findByDoctorAndAppointmentDateBetween(appointment.getDoctor(), startDay, endDay);
        if (appointmentsDay.size() >= 8) {
            return false;
        }
        appointmentRepository.save(appointment);
        return true;
    }

    public List<Appointment> getGeneralAppointments(){
        return  appointmentRepository.findAll();
    }

    public List<Appointment> findValidAppointments(Doctor doctor, Office office, LocalDateTime start, LocalDateTime end) {
        return appointmentRepository.findByDoctorAndOfficeAndAppointmentDateBetween(doctor, office, start, end);
    }

}
