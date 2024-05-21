package ma.gest_dentaire.Controller;


import ma.gest_dentaire.model.entity.Personne;
import ma.gest_dentaire.service.PersonneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Personne")
public class PersonneController {

    @Autowired
    private PersonneService personneService;

    @GetMapping("/get-personne")
    public Personne getPersonnes(@RequestParam Integer id) {
        return (Personne) personneService.getPersonnes(id);
    }

    @GetMapping("/Delete-personne")
    public void deletePersonne(@RequestParam Integer id) {
        personneService.delete(id);
    }

    @GetMapping("/Delete-AllPersonne")
    public void deleteAllPersonne() {
        personneService.deleteAll();
    }

    @GetMapping("/Get-AllPersonne")
    public List<Personne> getAllPersonne() {
        if (personneService.getAllPersonne().isEmpty()) {
            System.out.println("Personne n'existe pas");
        }
            return personneService.getAllPersonne();
    }

    @PostMapping("/Add-Personne")
    public Personne addPersonne(@RequestBody Personne personne) {
        return personneService.addPersonne(personne);
    }
}

