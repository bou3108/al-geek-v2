package fr.afcepf.algeek.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class Client implements Serializable {

    private Long id;
    private String nom;
    private String prenom;
    private String email;
    private String login;
    private String hashedPassword;
    private String salt;
    private String adresse;
    private String codePostal;
    private String ville;


    public Client(String nom, String prenom,
                  String email, String login) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.login = login;
    }

    public Client(String nom, String prenom, String email, String login, String hashedPassword, String salt,
                  String adresse, String codePostal, String ville) {
        super();
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.login = login;
        this.hashedPassword = hashedPassword;
        this.salt = salt;
        this.adresse = adresse;
        this.codePostal = codePostal;
        this.ville = ville;
    }

}
