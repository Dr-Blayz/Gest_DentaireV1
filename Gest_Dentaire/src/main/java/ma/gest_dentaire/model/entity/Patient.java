package ma.gest_dentaire.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import ma.gest_dentaire.model.enumclass.GroupeSanguin;
import ma.gest_dentaire.model.enumclass.Mutuelle;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Patient")
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_Patient")
    private Integer id_Patient;

    @Column(name = "cin_Patient")
    private String Cin_Patient;

    @Column(name = "nom_Patient")
    private String nom_Patient;

    @Column(name = "prenom_Patient")
    private String prenom_Patient;

    @Column(name = "email_Patient")
    private String email_Patient;

    @Column(name = "tel_Patient")
    private String tel_Patient;

    @Column(name = "adresse_Patient")
    private String adresse_Patient;

    @Column(name = "date_naissance")
    private LocalDate dateNaissance;

    @Enumerated(EnumType.STRING)
    @Column(name = "mutuelle")
    private Mutuelle mutuelle;

    @Enumerated(EnumType.STRING)
    @Column(name = "group_sanguin")
    private GroupeSanguin groupeSanguin;

    @Column(name = "antecedent_medicale")
    private String antecedentMedicale;

    @Column(name = "profession")
    private String profession;

    @JsonIgnore
    @OneToOne(mappedBy = "patient", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private DossierMedical dossierMedicale;


    @Override
    public String toString() {
        return "Patient{" +
                "profession='" + profession + '\'' +
                ", antecedentMedicale='" + antecedentMedicale + '\'' +
                ", groupeSanguin=" + groupeSanguin +
                ", mutuelle=" + mutuelle +
                ", dateNaissance=" + dateNaissance +
                ", adresse_Patient='" + adresse_Patient + '\'' +
                ", tel_Patient='" + tel_Patient + '\'' +
                ", email_Patient='" + email_Patient + '\'' +
                ", prenom_Patient='" + prenom_Patient + '\'' +
                ", nom_Patient='" + nom_Patient + '\'' +
                ", Cin_Patient='" + Cin_Patient + '\'' +
                ", id_Patient=" + id_Patient +
                '}';
    }
}
