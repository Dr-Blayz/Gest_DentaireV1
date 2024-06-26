package ma.gest_dentaire.service;

import jakarta.transaction.Transactional;
import ma.gest_dentaire.model.entity.Patient;
import ma.gest_dentaire.repository.PatientRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientService {

    private final PatientRepo patientRepo;

    @Autowired
    public PatientService(PatientRepo patientRepo) {
        this.patientRepo = patientRepo;
    }



    public int getTotalPatients() {
        return (int) patientRepo.count();
    }




    @Transactional
    public Patient addPatient(Patient patient) {
        return patientRepo.save(patient);
    }

    public List<Patient> getAllPatients() {
        return patientRepo.findAll();
    }

    public Patient getPatientById(Integer id) {
        return patientRepo.findById(id).orElse(null);
    }

    @Transactional
    public void updatePatient(Patient patient) {
        patientRepo.save(patient);
    }

    @Transactional
    public void deletePatient(Integer id) {
        patientRepo.deleteById(id);
    }
}
