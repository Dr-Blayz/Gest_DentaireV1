package ma.gest_dentaire.service;

import ma.gest_dentaire.model.entity.Personne;
import ma.gest_dentaire.repository.PersonneRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Service
public class PersonneService {


    @Autowired
    private PersonneRepo personneRepo;

    public Personne getPersonnes(Integer id) {
        Optional<Personne> personne = this.personneRepo.findById(id);
        return personne.orElse(null);
    }

    public ResponseEntity<Personne> createPersonne(Personne nouvellePersonne) {
        Personne personneAjoutee = personneRepo.save(nouvellePersonne);
        return ResponseEntity.ok(personneAjoutee);
    }

    public void delete(Integer id) {
        this.personneRepo.deleteById(id);
    }

    public void deleteAll() {
        this.personneRepo.deleteAll();
    }

    public List<Personne> getAllPersonne() {
        return personneRepo.findAll();
    }

    public Personne addPersonne(Personne personne) {
        return this.personneRepo.save(personne);
    }

}

