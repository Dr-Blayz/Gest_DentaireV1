package ma.gest_dentaire.service;

import ma.gest_dentaire.model.entity.Consultation;
import ma.gest_dentaire.model.entity.Patient;
import ma.gest_dentaire.repository.ConsultationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;

@Service
public class ConsultationService {
    private final ConsultationRepo consultationRepository;

    @Autowired
    public ConsultationService(ConsultationRepo consultationRepository) {
        this.consultationRepository = consultationRepository;
    }

    public List<Consultation> getConsultationsByDossierId(Integer dossierId) {
        return consultationRepository.findByDossierMedical_IdDossier(dossierId);
    }

    public void addConsultationToDossier(Consultation consultation) {
        consultationRepository.save(consultation);
    }

    public int getTotalConsultationsThisMonth() {
        return consultationRepository.countConsultationsThisMonth();
    }

    public void supprimerConsultation(Consultation consultation) {
        consultationRepository.delete(consultation);
    }

    public Consultation getConsultationById(Integer id) {
        return consultationRepository.findById(id).orElse(null);
    }

    public double calculateTotalRevenueForCurrentMonth() {
        // Récupérer la date de début et de fin du mois actuel
        LocalDate firstDayOfMonth = LocalDate.now().withDayOfMonth(1);
        LocalDate lastDayOfMonth = LocalDate.now().withDayOfMonth(LocalDate.now().lengthOfMonth());

        // Récupérer toutes les consultations pour le mois actuel
        List<Consultation> consultations = consultationRepository.findAllByDateConsultationBetween(firstDayOfMonth, lastDayOfMonth);

        // Calculer le total des prix des consultations
        double totalRevenue = consultations.stream()
                .mapToDouble(Consultation::getPrix)
                .sum();

        return totalRevenue;
    }
}
