package ma.gest_dentaire.Controller;

import ma.gest_dentaire.model.entity.DossierMedical;
import ma.gest_dentaire.model.entity.Patient;
import ma.gest_dentaire.model.enumclass.GroupeSanguin;
import ma.gest_dentaire.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/patients")
public class PatientController {

    private final PatientService patientService;

    @Autowired
    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping
    public String listPatients(Model model) {
        List<Patient> patients = patientService.getAllPatients();
        model.addAttribute("patients", patients);
        return "Pages/patients"; // Nom du fichier Thymeleaf sans extension
    }

    @GetMapping("/{id}")
    public String getPatientDetailsPage(@PathVariable Integer id, Model model) {
        Patient patient = patientService.getPatientById(id);
        if (patient != null) {
            model.addAttribute("patient", patient);
            return "Pages/detailPatient"; // Assurez-vous que le nom correspond au nom de votre fichier HTML
        } else {
            model.addAttribute("errorMessage", "Patient introuvable !");
            return "redirect:/patients";
        }
    }

    @GetMapping("/add")
    public String showAddPatientForm(Model model) {
        model.addAttribute("patient", new Patient());
        return "Pages/AddPatient"; // Assurez-vous que le nom correspond au nom de votre fichier HTML
    }

    @PostMapping("/add")
    public String addPatient(@ModelAttribute Patient patient, Model model) {
        DossierMedical dossierMedical = new DossierMedical(LocalDate.now(), patient, "Situation financière non renseignée");
        patient.setDossierMedicale(dossierMedical);
        patientService.addPatient(patient);
        model.addAttribute("successMessage", "Patient ajouté avec succès !");
        return "redirect:/patients"; // Rediriger vers la liste des patients après ajout
    }

    @GetMapping("/{id}/dossier")
    public String showEditDossierMedicalForm(@PathVariable Integer id, Model model) {
        Patient patient = patientService.getPatientById(id);
        if (patient != null) {
            model.addAttribute("patient", patient);
            return "Pages/ModifierDossier"; // Nom du fichier Thymeleaf sans extension
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
            // Vérifiez si le dossier médical du patient est null
            if (existingPatient.getDossierMedicale() == null) {
                // Si c'est le cas, initialisez-le avec un nouveau dossier médical
                existingPatient.setDossierMedicale(new DossierMedical());
            }
            // Mettez à jour les informations du dossier médical
            existingPatient.getDossierMedicale().setSituationFinanciere(situationFinanciere);
            // Utilisez la date fournie dans la requête
            existingPatient.getDossierMedicale().setDateCreation(date);
            patientService.updatePatient(existingPatient);
            model.addAttribute("successMessage", "Dossier médical mis à jour avec succès !");
            return "redirect:/patients/"+ id;
        } else {
            model.addAttribute("errorMessage", "Patient introuvable !");
            return "redirect:/patients";
        }
    }

    @PostMapping("/{id}/update")
    public String updatePatient(@PathVariable Integer id, @ModelAttribute Patient updatedPatient, Model model) {
        Patient existingPatient = patientService.getPatientById(id);
        if (existingPatient != null) {
            // Mettez à jour les informations du patient avec celles du formulaire
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

            // Enregistrez les modifications
            patientService.updatePatient(existingPatient);
            model.addAttribute("successMessage", "Informations du patient mises à jour avec succès !");
            return "redirect:/patients/" + id;
        } else {
            model.addAttribute("errorMessage", "Patient introuvable !");
            return "redirect:/patients";
        }
    }

    @ModelAttribute("enumValues")
    public GroupeSanguin[] getEnumValues() {
        return GroupeSanguin.values(); // Return all enum values as an array
    }

}
