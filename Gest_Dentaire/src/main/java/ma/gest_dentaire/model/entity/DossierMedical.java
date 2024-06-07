package ma.gest_dentaire.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class DossierMedical {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idDossier;

    private LocalDate dateCreation;

    @JsonIgnore
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_Patient")
    private Patient patient;

    private String situationFinanciere;

    @OneToMany(mappedBy = "dossierMedical", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Consultation> consultations;

    public DossierMedical(LocalDate dateCreation, Patient patient, String situationFinanciere) {
        this.dateCreation = dateCreation;
        this.patient = patient;
        this.situationFinanciere = situationFinanciere;
    }

    public DossierMedical(LocalDate dateCreation, Patient patient, String situationFinanciere, List<Consultation> consultations) {
        this.dateCreation = dateCreation;
        this.patient = patient;
        this.situationFinanciere = situationFinanciere;
        this.consultations = consultations;
    }


    @Override
    public String toString() {
        return "DossierMedical{" +
                "idDossier=" + idDossier +
                ", dateCreation=" + dateCreation +
                ", patient=" + patient +
                ", situationFinanciere='" + situationFinanciere + '\'' +
                '}';
    }
}
