package ma.gest_dentaire.repository;

import ma.gest_dentaire.model.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface PatientRepo extends JpaRepository<Patient, Integer> {


}
