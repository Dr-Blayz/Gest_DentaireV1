package ma.gest_dentaire.Controller;


import ma.gest_dentaire.service.ConsultationService;
import ma.gest_dentaire.service.DossierMedicalService;
import ma.gest_dentaire.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")

public class HomeController {

    private final PatientService patientService;
    private final ConsultationService consultationService;
    private final DossierMedicalService dossierMedicalService;

    @Autowired
    public HomeController(PatientService patientService, ConsultationService consultationService, DossierMedicalService dossierMedicalService) {
        this.patientService = patientService;
        this.consultationService = consultationService;
        this.dossierMedicalService = dossierMedicalService;
    }

    @GetMapping("/")
    public String Home(Model model) {
        int totalPatients = patientService.getTotalPatients();
        model.addAttribute("totalPatients", totalPatients);


        // Nombre total de dossiers médicaux avec la situation "Payé"
        int totalDossiersPayes = dossierMedicalService.getTotalDossiersWithSituationPaye();
        model.addAttribute("totalDossiersPayes", totalDossiersPayes);

        // Nombre total de consultations du mois courant
        int totalConsultationsThisMonth = consultationService.getTotalConsultationsThisMonth();
        model.addAttribute("totalConsultationsThisMonth", totalConsultationsThisMonth);

        // Calculer le total des prix des consultations pour le mois actuel
        double totalRevenue = consultationService.calculateTotalRevenueForCurrentMonth();

        // Ajouter le total des revenus du mois au modèle
        model.addAttribute("totalRevenue", totalRevenue);



        return "Pages/Home";
    }
}
