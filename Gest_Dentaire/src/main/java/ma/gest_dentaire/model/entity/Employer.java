package ma.gest_dentaire.model.entity;

import jakarta.persistence.*;
import lombok.*;
import ma.gest_dentaire.model.enumclass.Assurance;
import ma.gest_dentaire.model.enumclass.Fonction;
import ma.gest_dentaire.model.enumclass.Statuts;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "Employer")
public class Employer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_Employer")
    private Integer id_Employer;

    @Column(name = "cin_Employer", nullable = false)
    private String Cin_Employer;

    @Column(name = "nom_Employer", nullable = false)
    private String nom_Employer;

    @Column(name = "prenom_Employer", nullable = false)
    private String prenom_Employer;

    @Column(name = "email_Employer", nullable = false)
    private String email_Employer;

    @Column(name = "tel_Employer", nullable = false)
    private String tel_Employer;

    @Column(name = "adresse_Employer", nullable = false)
    private String adresse_Employer;

    @Column(name = "date_naissance", nullable = false)
    private LocalDate dateNaissance;

    @Column(name = "SalairteBase", nullable = false)
    private double SalairteBase;

    @Column(name = "prime", nullable = false)
    private double prime;

    @Column(name = "jours_Conge", nullable = false)
    private Integer jours_Conge;

    @Enumerated(EnumType.STRING)
    @Column(name = "Assurance", nullable = false)
    private Assurance assurance;

    @Enumerated(EnumType.STRING)
    @Column(name = "Statuts", nullable = false)
    private Statuts statuts;

    @Enumerated(EnumType.STRING)
    @Column(name = "Fonction", nullable = false)
    private Fonction fonction;
}
