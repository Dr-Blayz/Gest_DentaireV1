package ma.gest_dentaire.model.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.gest_dentaire.model.enumclass.GroupeSanguin;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Patient")
public class Patient extends Personne {

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

    public Patient(Integer id_Personne, String Cin_Personne, String nom_Personne, String prenom_Personne, String email_Personne, int tel_Personne, String adresse_Personne, String dateNaissance, String mutuelle, GroupeSanguin groupeSanguin, String antecedentMedicale, String dossierMedicale, String profession) {
        super(id_Personne, Cin_Personne, nom_Personne, prenom_Personne, email_Personne, tel_Personne, adresse_Personne);
        this.dateNaissance = dateNaissance;
        this.mutuelle = mutuelle;
        this.groupeSanguin = groupeSanguin;
        this.antecedentMedicale = antecedentMedicale;
        this.dossierMedicale = dossierMedicale;
        this.profession = profession;
    }
}
