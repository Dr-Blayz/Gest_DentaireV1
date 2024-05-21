package ma.gest_dentaire.service;


import ma.gest_dentaire.model.entity.Patient;
import ma.gest_dentaire.model.entity.Personne;
import ma.gest_dentaire.repository.PatientRepo;
import ma.gest_dentaire.repository.PersonneRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PatientService {

    @Autowired
    private PatientRepo patientRepo;
    @Autowired
    private PersonneRepo personneRepo;

    public Patient addPatient(Integer personneId, Patient patient) {
        // Récupérer la personne existante par son ID
        Personne existingPerson = personneRepo.findById(personneId).orElse(null);

        // Vérifier si la personne existe
        if (existingPerson != null) {
            // Ajouter les détails du patient aux informations de la personne
            existingPerson.setNom_Personne(patient.getNom_Personne());
            existingPerson.setPrenom_Personne(patient.getPrenom_Personne());
            existingPerson.setEmail_Personne(patient.getEmail_Personne());
            existingPerson.setTel_Personne(patient.getTel_Personne());
            existingPerson.setAdresse_Personne(patient.getAdresse_Personne());

            // Créer un nouvel objet Patient avec les informations de la personne et les détails supplémentaires du patient
            Patient patientWithDetails = new Patient(existingPerson.getId_Personne(), existingPerson.getCin_Personne(), existingPerson.getNom_Personne(), existingPerson.getPrenom_Personne(), existingPerson.getEmail_Personne(), existingPerson.getTel_Personne(), existingPerson.getAdresse_Personne(), patient.getDateNaissance(), patient.getMutuelle(), patient.getGroupeSanguin(), patient.getAntecedentMedicale(), patient.getDossierMedicale(), patient.getProfession());
            // Sauvegarder le patient dans la base de données
            return patientRepo.save(patientWithDetails);
        }

        return null;
    }
}
