package ma.gest_dentaire.controller;

import ma.gest_dentaire.model.entity.Consultation;
import ma.gest_dentaire.model.entity.DossierMedical;
import ma.gest_dentaire.model.entity.Patient;
import ma.gest_dentaire.model.enumclass.Acte;
import ma.gest_dentaire.model.enumclass.GroupeSanguin;
import ma.gest_dentaire.model.enumclass.Mutuelle;
import ma.gest_dentaire.service.ConsultationService;
import ma.gest_dentaire.service.DossierMedicalService;
import ma.gest_dentaire.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;

@Controller
@RequestMapping("/patients")
public class PatientController {

    private final PatientService patientService;
    private final ConsultationService consultationService;
    private final DossierMedicalService dossierMedicalService;

    @Autowired
    public PatientController(PatientService patientService, DossierMedicalService dossierMedicalService, ConsultationService consultationService) {
        this.patientService = patientService;
        this.consultationService = consultationService;
        this.dossierMedicalService = dossierMedicalService;
    }

    @GetMapping
    public String listPatients(Model model) {
        List<Patient> patients = patientService.getAllPatients();
        patients.sort(Comparator.comparing(Patient::getId_Patient).reversed());
        model.addAttribute("patients", patients);
        return "Pages/patients";
    }

    @GetMapping("/{id}")
    public String getPatientDetailsPage(@PathVariable Integer id, Model model) {
        Patient patient = patientService.getPatientById(id);
        if (patient != null) {
            DossierMedical dossierMedical = dossierMedicalService.getDossierById(id);
            List<Consultation> consultations = consultationService.getConsultationsByDossierId(dossierMedical.getIdDossier());
            model.addAttribute("patient", patient);
            model.addAttribute("dossierMedical", dossierMedical);
            model.addAttribute("consultations", consultations);
            return "Pages/detailPatient";
        } else {
            model.addAttribute("errorMessage", "Patient introuvable !");
            return "redirect:/patients";
        }
    }

    @GetMapping("/add")
    public String showAddPatientForm(Model model) {
        model.addAttribute("patient", new Patient());
        return "Pages/AddPatient";
    }

    @PostMapping("/add")
    public String addPatient(@ModelAttribute Patient patient, Model model) {
        DossierMedical dossierMedical = new DossierMedical(LocalDate.now(), patient, "Situation financière non renseignée", null);
        patient.setDossierMedicale(dossierMedical);
        patientService.addPatient(patient);
        model.addAttribute("successMessage", "Patient ajouté avec succès !");
        return "redirect:/patients";
    }

    @GetMapping("/{id}/dossier")
    public String showEditDossierMedicalForm(@PathVariable Integer id, Model model) {
        Patient patient = patientService.getPatientById(id);
        if (patient != null) {
            model.addAttribute("patient", patient);
            return "Pages/ModifierDossier";
        } else {
            model.addAttribute("errorMessage", "Patient introuvable !");
            return "redirect:/patients";
        }
    }

    @PostMapping("/{id}/dossier/update")
    public String updatePatientDossier(@PathVariable Integer id, @RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
                                       @RequestParam("situationFinanciere") String situationFinanciere, Model model) {
        Patient existingPatient = patientService.getPatientById(id);
        if (existingPatient != null) {
            if (existingPatient.getDossierMedicale() == null) {
                existingPatient.setDossierMedicale(new DossierMedical());
            }
            existingPatient.getDossierMedicale().setSituationFinanciere(situationFinanciere);
            existingPatient.getDossierMedicale().setDateCreation(date);
            patientService.updatePatient(existingPatient);
            model.addAttribute("successMessage", "Dossier médical mis à jour avec succès !");
            return "redirect:/patients/" + id;
        } else {
            model.addAttribute("errorMessage", "Patient introuvable !");
            return "redirect:/patients";
        }
    }

    @PostMapping("/{id}/update")
    public String updatePatient(@PathVariable Integer id, @ModelAttribute Patient updatedPatient, Model model) {
        Patient existingPatient = patientService.getPatientById(id);
        if (existingPatient != null) {
            existingPatient.setCin_Patient(updatedPatient.getCin_Patient());
            existingPatient.setNom_Patient(updatedPatient.getNom_Patient());
            existingPatient.setPrenom_Patient(updatedPatient.getPrenom_Patient());
            existingPatient.setEmail_Patient(updatedPatient.getEmail_Patient());
            existingPatient.setTel_Patient(updatedPatient.getTel_Patient());
            existingPatient.setAdresse_Patient(updatedPatient.getAdresse_Patient());
            existingPatient.setDateNaissance(updatedPatient.getDateNaissance());
            existingPatient.setMutuelle(updatedPatient.getMutuelle());
            existingPatient.setGroupeSanguin(updatedPatient.getGroupeSanguin());
            existingPatient.setAntecedentMedicale(updatedPatient.getAntecedentMedicale());
            existingPatient.setProfession(updatedPatient.getProfession());

            patientService.updatePatient(existingPatient);
            model.addAttribute("successMessage", "Informations du patient mises à jour avec succès !");
            return "redirect:/patients/" + id;
        } else {
            model.addAttribute("errorMessage", "Patient introuvable !");
            return "redirect:/patients";
        }
    }

    @GetMapping("/{id}/delete")
    public String deletePatient(@PathVariable Integer id, Model model) {
        Patient patient = patientService.getPatientById(id);
        if (patient == null) {
            model.addAttribute("errorMessage", "Patient introuvable !");
            return "redirect:/patients";
        }
        // Supprimer les consultations associées au dossier médical du patient
        DossierMedical dossierMedical = patient.getDossierMedicale();
        List<Consultation> consultations = dossierMedical.getConsultations();
        for (Consultation consultation : consultations) {
            consultationService.supprimerConsultation(consultation);
        }
        // Supprimer le dossier médical du patient
        dossierMedicalService.supprimerDossierMedical(dossierMedical.getIdDossier());
        // Supprimer le patient
        patientService.deletePatient(id);
        model.addAttribute("successMessage", "Patient supprimé avec succès !");
        return "redirect:/patients";
    }


    @GetMapping("/{id}/consultation/add")
    public String showAddConsultationForm(@PathVariable("id") Integer dossierId, Model model) {
        DossierMedical dossierMedical = dossierMedicalService.getDossierById(dossierId);
        if (dossierMedical == null) {
            model.addAttribute("errorMessage", "Dossier médical introuvable !");
            return "redirect:/patients";
        }
        model.addAttribute("consultation", new Consultation());
        model.addAttribute("dossierId", dossierId);
        model.addAttribute("acteValues", Acte.values());
        return "Pages/AddConsultation";
    }

    @PostMapping("/{id}/consultation/add")
    public String addConsultation(@PathVariable("id") Integer dossierId,
                                  @RequestParam("dateConsultation") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateConsultation,
                                  @RequestParam("acte") Acte acte,
                                  @RequestParam("prix") Double prix,
                                  @RequestParam("description") String description,
                                  Model model) {
        DossierMedical dossierMedical = dossierMedicalService.getDossierById(dossierId);
        if (dossierMedical == null) {
            model.addAttribute("errorMessage", "Dossier médical introuvable !");
            return "redirect:/patients";
        }
        Consultation consultation = new Consultation();
        consultation.setDateConsultation(dateConsultation);
        consultation.setActe(acte);
        consultation.setPrix(prix);
        consultation.setDescription(description);
        consultation.setDossierMedical(dossierMedical);
        consultationService.addConsultationToDossier(consultation);
        model.addAttribute("successMessage", "Consultation ajoutée avec succès !");
        return "redirect:/patients/" + dossierMedical.getPatient().getId_Patient();
    }


    @GetMapping("/{id}/consultation/{consultationId}/delete") // Changer en GetMapping
    public String supprimerConsultation(@PathVariable("id") Integer dossierId, @PathVariable("consultationId") Integer consultationId, Model model) {
        DossierMedical dossierMedical = dossierMedicalService.getDossierById(dossierId);
        if (dossierMedical == null) {
            model.addAttribute("errorMessage", "Patient introuvable !");
            return "redirect:/patients/" + dossierMedical.getPatient().getId_Patient();

        }

        Consultation consultation = consultationService.getConsultationById(consultationId);
        if (consultation == null) {
            model.addAttribute("errorMessage", "Consultation introuvable !");
            return "redirect:/patients/" + dossierMedical.getPatient().getId_Patient();
        }

        // Supprimer la consultation du dossier médical
        consultationService.supprimerConsultation(consultation);

        model.addAttribute("successMessage", "Consultation supprimée avec succès !");
        return "redirect:/patients/" + dossierMedical.getPatient().getId_Patient();

    }

    @ModelAttribute("enumValues")
    public GroupeSanguin[] getEnumValues() {
        return GroupeSanguin.values();
    }

    @ModelAttribute("mutuelleValues")
    public Mutuelle[] getMutuelleValues() {
        return Mutuelle.values();
    }
}
