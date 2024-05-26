package ma.gest_dentaire.service;


import ma.gest_dentaire.model.entity.Patient;
import ma.gest_dentaire.repository.PatientRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientService {

    @Autowired
    private PatientRepo patientRepo;


    public Patient addPatient(Patient patient) {
        return patientRepo.save(patient);
    }

    public List<Patient> getAllPatients() {
        return patientRepo.findAll();
    }
}
