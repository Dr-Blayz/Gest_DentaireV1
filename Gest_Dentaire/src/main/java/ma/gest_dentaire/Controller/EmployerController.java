package ma.gest_dentaire.Controller;

import ma.gest_dentaire.model.entity.Employer;
import ma.gest_dentaire.model.enumclass.*;
import ma.gest_dentaire.service.EmployerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/employers")
public class EmployerController {

    private final EmployerService employerService;

    @Autowired
    public EmployerController(EmployerService employerService) {
        this.employerService = employerService;
    }

    @GetMapping
    public String listEmployers(Model model) {
        List<Employer> employers = employerService.getAllEmployers();
        model.addAttribute("employers", employers);
        return "Pages/employers";
    }

    @GetMapping("/add")
    public String showAddEmployerForm(Model model) {
        model.addAttribute("employer", new Employer());
        model.addAttribute("statutsValues", Statuts.values());
        model.addAttribute("assuranceValues", Assurance.values());
        model.addAttribute("fonctionValues", Fonction.values());
        return "Pages/AddEmployer";
    }

    @PostMapping("/add")
    public String addEmployer(@ModelAttribute Employer employer, Model model) {
        employerService.addEmployer(employer);
        model.addAttribute("successMessage", "Employé ajouté avec succès !");
        return "redirect:/employers";
    }

    @GetMapping("/{id}")
    public String getEmployerDetailsPage(@PathVariable Integer id, Model model) {
        Optional<Employer> employer = employerService.getEmployerById(id);
        if (employer.isPresent()) {
            model.addAttribute("employer", employer.get());
            return "Pages/detailEmployer";
        } else {
            model.addAttribute("errorMessage", "Employé introuvable !");
            return "redirect:/employers";
        }
    }

    @GetMapping("/{id}/edit")
    public String showUpdateEmployerForm(@PathVariable Integer id, Model model) {
        Optional<Employer> existingEmployer = employerService.getEmployerById(id);
        if (existingEmployer.isPresent()) {
            model.addAttribute("employer", existingEmployer.get());
            return "Pages/ModifierEmployer"; // Nom de la page HTML de modification d'employé
        } else {
            // Si l'employé n'est pas trouvé, redirigez vers une page d'erreur ou une autre page appropriée
            return "redirect:/employers";
        }
    }

    @PostMapping("/{id}/update")
    public String updateEmployer(@PathVariable Integer id, @ModelAttribute Employer updatedEmployer, Model model) {
        Optional<Employer> existingEmployer = employerService.getEmployerById(id);
        if (existingEmployer.isPresent()) {
            Employer employer = existingEmployer.get();
            employer.setCin_Employer(updatedEmployer.getCin_Employer());
            employer.setNom_Employer(updatedEmployer.getNom_Employer());
            employer.setPrenom_Employer(updatedEmployer.getPrenom_Employer());
            employer.setEmail_Employer(updatedEmployer.getEmail_Employer());
            employer.setTel_Employer(updatedEmployer.getTel_Employer());
            employer.setAdresse_Employer(updatedEmployer.getAdresse_Employer());
            employer.setDateNaissance(updatedEmployer.getDateNaissance());
            employer.setSalairteBase(updatedEmployer.getSalairteBase());
            employer.setPrime(updatedEmployer.getPrime());
            employer.setJours_Conge(updatedEmployer.getJours_Conge());
            employer.setAssurance(updatedEmployer.getAssurance());
            employer.setStatuts(updatedEmployer.getStatuts());
            employer.setFonction(updatedEmployer.getFonction());

            employerService.addEmployer(employer);  // Update the existing employer
            model.addAttribute("successMessage", "Informations de l'employé mises à jour avec succès !");
            return "redirect:/employers/" + id;
        } else {
            model.addAttribute("errorMessage", "Employé introuvable !");
            return "redirect:/employers";
        }
    }

    @GetMapping("/{id}/delete")
    public String deleteEmployer(@PathVariable Integer id, Model model) {
        employerService.deleteEmployer(id);
        model.addAttribute("successMessage", "Employé supprimé avec succès !");
        return "redirect:/employers";
    }

    @ModelAttribute("statutsValues")
    public Statuts[] getStatutsValues() {
        return Statuts.values();
    }

    @ModelAttribute("assuranceValues")
    public Assurance[] getAssuranceValues() {
        return Assurance.values();
    }

    @ModelAttribute("fonctionValues")
    public Fonction[] getFonctionValues() {
        return Fonction.values();
    }
}
