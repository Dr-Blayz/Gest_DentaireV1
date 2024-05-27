package ma.gest_dentaire.service;


import ma.gest_dentaire.model.entity.Patient;
import ma.gest_dentaire.repository.PatientRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PatientService {

    @Autowired
    private PatientRepo patientRepo;
    @Autowired
    public PatientService(PatientRepo patientRepo) {
        this.patientRepo = patientRepo;
    }

    public Patient addPatient(Patient patient) {
        return patientRepo.save(patient);
    }

    public List<Patient> getAllPatients() {
        return patientRepo.findAll();
    }

        public Patient getPatientById(Integer id) {
            return patientRepo.findById(id).orElse(null);
        }

}
