package com.example.kosmo.repository;

import com.example.kosmo.model.Appointment;
import com.example.kosmo.model.Doctor;
import com.example.kosmo.model.Office;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    List<Appointment> findByDoctorAndOfficeAndAppointmentDateBetween(Doctor doctor, Office office, LocalDateTime startDate, LocalDateTime endDate);
}
