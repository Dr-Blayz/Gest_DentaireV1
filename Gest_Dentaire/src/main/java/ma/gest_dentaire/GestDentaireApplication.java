package ma.gest_dentaire;

import ma.gest_dentaire.model.entity.Personne;
import ma.gest_dentaire.repository.PersonneRepo;
import ma.gest_dentaire.service.PersonneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

@SpringBootApplication
public class GestDentaireApplication implements CommandLineRunner {

    private final PersonneRepo personneRepo;
    private final PersonneService personneService;

    public GestDentaireApplication(PersonneRepo personneRepo, PersonneService personneService) {
        this.personneRepo = personneRepo;
        this.personneService = personneService;
    }

    public static void main(String[] args) {
        System.out.println("Hello World");
        System.out.println("Test");
        SpringApplication.run(GestDentaireApplication.class, args);
    }


    @Override
    public void run(String... args) throws Exception {


        Iterable<Personne> personnes = personneRepo.findAll();

        // Afficher les personnes récupérées
        for (Personne personne : personnes) {
            System.out.println(personne);
        }

    }
}
