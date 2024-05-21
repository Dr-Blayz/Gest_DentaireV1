package ma.gest_dentaire.repository;

import ma.gest_dentaire.model.entity.Personne;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;



public interface PersonneRepo extends JpaRepository<Personne, Integer> {


}
