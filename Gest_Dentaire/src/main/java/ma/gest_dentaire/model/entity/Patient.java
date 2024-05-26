package ma.gest_dentaire.model.entity;


import jakarta.persistence.*;
import lombok.*;
import ma.gest_dentaire.model.enumclass.GroupeSanguin;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Patient")
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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
    private int tel_Patient;

    @Column(name = "adresse_Patient")
    private String adresse_Patient;

    @Column(name = "date_naissance")
    private String dateNaissance;

    @Column(name = "mutuelle")
    private String mutuelle;

    @Column(name = "group_sanguin")
    @Enumerated(EnumType.STRING)
    private GroupeSanguin groupeSanguin;

    @Column(name = "antecedent_medicale")
    private String antecedentMedicale;

    @Column(name = "dossier_medicale")
    private String dossierMedicale;

    @Column(name = "profession")
    private String profession;

    public Patient(String profession, String dossierMedicale, String antecedentMedicale, GroupeSanguin groupeSanguin, String mutuelle, String dateNaissance, String adresse_Patient, int tel_Patient, String email_Patient, String prenom_Patient, String nom_Patient, String Cin_Patient) {
        this.Cin_Patient = Cin_Patient;
        this.prenom_Patient = prenom_Patient;
        this.nom_Patient = nom_Patient;
        this.adresse_Patient = adresse_Patient;
        this.tel_Patient = tel_Patient;
        this.email_Patient = email_Patient;
        this.dateNaissance = dateNaissance;
        this.profession = profession;
        this.dossierMedicale = dossierMedicale;
        this.antecedentMedicale = antecedentMedicale;
        this.groupeSanguin = groupeSanguin;
        this.mutuelle = mutuelle;



    }


}
