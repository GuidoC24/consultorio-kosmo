package com.example.kosmo.controller;

import com.example.kosmo.model.*;
import com.example.kosmo.repository.AppointmentRepository;
import com.example.kosmo.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Controller
public class IndexController {

    @Autowired
    private DoctorService doctorService;

    @Autowired
    private PatientService patientService;

    @Autowired
    private AppointmentService appointmentService;

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private SpecialtyService specialtyService;

    @Autowired
    private OfficeService officeService;

    private static final Logger LOGGER = LoggerFactory.getLogger(IndexController.class);


    @GetMapping("/")
    public String index(@RequestParam(required = false) Long doctorId,
                        @RequestParam(required = false) Long officeId,
                        @RequestParam(required = false) LocalDate appointmentDate, Model model) {
        List<Appointment> appointmentList = null;
        if (doctorId != null && officeId != null && appointmentDate != null) {
            Doctor doctor = doctorService.findDoctorById(doctorId).orElseThrow();
            Office office = officeService.findOfficeById(officeId).orElseThrow();
            LocalDateTime start = appointmentDate.atStartOfDay();
            LocalDateTime end = appointmentDate.plusDays(1).atStartOfDay();
            LOGGER.info("Doctor: {}, Office: {}, Start: {}, End {}", doctor, office, start, end);
            appointmentList = appointmentService.findValidAppointments(doctor, office, start, end);
            LOGGER.info("Appointments: {}", appointmentList);
        }
        model.addAttribute("patients", patientService.getPatients());
        model.addAttribute("doctors", doctorService.getDoctors());
        model.addAttribute("offices", officeService.getOffices());
        model.addAttribute("appointments", appointmentList);
        model.addAttribute("generalAppointments", appointmentService.getGeneralAppointments());
        return "index";
    }

    @GetMapping("/patients/add")
    public String getPatients() {
        return "addPatients";
    }

    @GetMapping("/specialties/add")
    public String getSpecialties() {
        return "addSpecialty";
    }

    @GetMapping("/appointments/add")
    public String getAppointments(Model model) {
        model.addAttribute("patients", patientService.getPatients());
        model.addAttribute("doctors", doctorService.getDoctors());
        model.addAttribute("offices", officeService.getOffices());
        return "addAppointment";
    }

    @GetMapping("/doctors/add")
    public String getDoctors() {
        return "addDoctors";
    }

    @GetMapping("/offices/add")
    public String getOffices() {
        return "addOffice";
    }

    @PostMapping("/addDoctor")
    public String addDoctor(Doctor doctor) {
        doctorService.addDoctor(doctor);
        return "redirect:/";
    }

    @PostMapping("/addSpecialty")
    public String addSpecialty(Specialty specialty) {
        specialtyService.addSpecialty(specialty);
        return "redirect:/";
    }

    @PostMapping("/addPatient")
    public String addPatient(Patient patient, Model model) {
        if(patientService.addPatient(patient)) {
            return "redirect:/";
        } else {
            model.addAttribute("errorMsg", "Error al registrar paciente. Verifique que los datos sean correctos");
            return "resultPage";
        }
    }

    @PostMapping("/addAppointment")
    public String addAppointment(Appointment appointment, Model model) {
        if(appointmentService.addAppointment(appointment)){
            return "redirect:/";
        }else{
            model.addAttribute("errorMsg", "Error al registrar cita. Existe un conflicto con las reglas establecidas");
            return "resultPage";
        }
    }

    @PostMapping("/addOffice")
    public String addOffices(Office office) {
        officeService.addOffice(office);
        return "redirect:/";
    }

    @PostMapping("/deleteAppointment")
    public String deleteAppointment(@RequestParam Long citaId, Model model) {
        appointmentRepository.deleteById(citaId);
        return "index";
    }

}
