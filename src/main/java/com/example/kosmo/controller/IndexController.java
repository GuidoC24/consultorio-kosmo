package com.example.kosmo.controller;

import com.example.kosmo.model.*;
import com.example.kosmo.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class IndexController {

    @Autowired
    private DoctorService doctorService;

    @Autowired
    private PatientService patientService;

    @Autowired
    private AppointmentService appointmentService;

    @Autowired
    private SpecialtyService specialtyService;

    @Autowired
    private OfficeService officeService;

    private static final Logger LOGGER = LoggerFactory.getLogger(IndexController.class);


    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("appointments", appointmentService.getAppointments());
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
    public String getDoctors() {
        return "addDoctors";
    }

    @GetMapping("/doctors/add")
    public String getAppointments() {
        return "addAppointment";
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
            model.addAttribute("successMsg", "Paciente registrado existosamente");
            return "redirect:/";
        } else {
            model.addAttribute("errorMsg", "Error al registrar paciente. Verifique que los datos sean correctos");
            return "resultPage";
        }
    }

    @PostMapping("/addAppointment")
    public String addAppointment(Appointment appointment) {
        appointmentService.addAppointment(appointment);
        return "redirect:/";
    }

    @PostMapping("/addOffices")
    public String addOffices(Office office) {
        officeService.addOffice(office);
        return "redirect:/";
    }
}
