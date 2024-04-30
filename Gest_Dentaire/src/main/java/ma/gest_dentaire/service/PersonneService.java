package ma.gest_dentaire.service;

import ma.gest_dentaire.model.entity.Personne;
import ma.gest_dentaire.repository.PersonneRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.*;

import java.util.Optional;

@Service
public class PersonneService {


    @Autowired
    private PersonneRepo personneRepo;

    public Iterable<Personne> getPersonne() {
        return personneRepo.findAll();
    }

}
