package ma.gest_dentaire.model.entity;


import jakarta.persistence.*;
import lombok.*;

@Data @AllArgsConstructor @NoArgsConstructor
@Entity
@Builder
@Table(name = "Personne")
public class Personne {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_Personne")
    private Integer id_Personne;

    @Column(name = "cin_Personne")
    private String Cin_Personne;

    @Column(name = "nom_Personne")
    private String nom_Personne;

    @Column(name = "prenom_Personne")
    private String prenom_Personne;

    @Column(name = "email_Personne")
    private String email_Personne;

    @Column(name = "tel_Personne")
    private int tel_Personne;

    @Column(name = "adresse_Personne")
    private String adresse_Personne;

    public Personne(String cin_Personne, String nom_Personne, String prenom_Personne, String email_Personne, int tel_Personne, String adresse_Personne) {
        Cin_Personne = cin_Personne;
        this.nom_Personne = nom_Personne;
        this.prenom_Personne = prenom_Personne;
        this.email_Personne = email_Personne;
        this.tel_Personne = tel_Personne;
        this.adresse_Personne = adresse_Personne;
    }
}
