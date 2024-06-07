package ma.gest_dentaire.service;

import jakarta.transaction.Transactional;
import ma.gest_dentaire.model.entity.DossierMedical;
import ma.gest_dentaire.repository.DossierMedicalRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DossierMedicalService {

    private final DossierMedicalRepo dossierMedicalRepository;

    @Autowired
    public DossierMedicalService(DossierMedicalRepo dossierMedicalRepository) {
        this.dossierMedicalRepository = dossierMedicalRepository;
    }

    public int getTotalDossiersWithSituationPaye() {
        return dossierMedicalRepository.countBySituationFinanciereIgnoreCase("Payé");
    }

    public DossierMedical getDossierById(Integer dossierId) {
        return dossierMedicalRepository.findById(dossierId).orElse(null);
    }

    @Transactional
    public void supprimerDossierMedical(Integer id) {
        dossierMedicalRepository.deleteById(id);
    }

    // Implement other necessary methods such as add, update, delete, etc.
}
