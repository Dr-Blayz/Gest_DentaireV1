package ma.gest_dentaire.repository;

import ma.gest_dentaire.model.entity.Consultation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;


public interface ConsultationRepo extends JpaRepository<Consultation, Integer> {
    List<Consultation> findByDossierMedical_IdDossier(Integer dossierId);

    @Query("SELECT COUNT(c) FROM Consultation c WHERE MONTH(c.dateConsultation) = MONTH(CURRENT_DATE) AND YEAR(c.dateConsultation) = YEAR(CURRENT_DATE)")
    int countConsultationsThisMonth();

    List<Consultation> findAllByDateConsultationBetween(LocalDate startDate, LocalDate endDate);

}
