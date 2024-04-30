package ma.gest_dentaire;

import ma.gest_dentaire.model.entity.Personne;
import ma.gest_dentaire.repository.PersonneRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GestDentaireApplication implements CommandLineRunner {

    private final PersonneRepo personneRepo;

    public GestDentaireApplication(PersonneRepo personneRepo) {
        this.personneRepo = personneRepo;
    }

    public static void main(String[] args) {
        System.out.println("Hello World");

        SpringApplication.run(GestDentaireApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Iterable<Personne> personnes = personneRepo.findAll();
        personnes.forEach(personne -> System.out.println(Personne.getNom_Personne()));
    }
}
