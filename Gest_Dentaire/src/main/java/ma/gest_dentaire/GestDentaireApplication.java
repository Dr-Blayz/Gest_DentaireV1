package ma.gest_dentaire;

import ma.gest_dentaire.model.entity.Patient;
import ma.gest_dentaire.repository.PatientRepo;
import ma.gest_dentaire.service.PatientService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GestDentaireApplication implements CommandLineRunner {


    private final PatientRepo patientRepo;
    private final PatientService patientService;

    public GestDentaireApplication(PatientRepo patientRepo, PatientService patientService) {
        this.patientRepo = patientRepo;
        this.patientService = patientService;
    }

    public static void main(String[] args) {
        System.out.println("Hello World");
        System.out.println("Test");
        SpringApplication.run(GestDentaireApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Iterable<Patient> patients = patientRepo.findAll();
        // Afficher les personnes récupérées
        for (Patient patient : patients) {
            System.out.println(patient);
        }
    }
}
