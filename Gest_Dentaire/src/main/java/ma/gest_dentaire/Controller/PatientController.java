package ma.gest_dentaire.controller;


import ma.gest_dentaire.model.entity.Patient;
import ma.gest_dentaire.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;


@Controller
public class PatientController {

    @Autowired
    private PatientService patientService;

    @GetMapping("/patients")
    public String listPatients(Model model) {
        List<Patient> patients = patientService.getAllPatients();
        model.addAttribute("patients", patients);
        return "patients"; // Nom du fichier Thymeleaf sans extension
    }
}
