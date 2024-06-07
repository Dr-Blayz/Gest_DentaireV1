package ma.gest_dentaire.repository;

import ma.gest_dentaire.model.entity.DossierMedical;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DossierMedicalRepo extends JpaRepository<DossierMedical, Integer> {

    int countBySituationFinanciereIgnoreCase(String payé);
}
