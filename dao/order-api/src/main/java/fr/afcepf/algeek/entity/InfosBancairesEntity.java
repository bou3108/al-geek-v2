package fr.afcepf.algeek.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "infos_bancaires")
public class InfosBancairesEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    public InfosBancairesEntity() {
        super();
    }

    public InfosBancairesEntity(
            Long id,
            String typeCarte,
            String numCarte,
            Date dateCarte,
            String cryptogramme) {

        super();
        this.id = id;
        this.typeCarte = typeCarte;
        this.numCarte = numCarte;
        this.dateCarte = dateCarte;
        this.cryptogramme = cryptogramme;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="type_carte")
    private String typeCarte;

    @Column(name="numero_carte")
    //@Size(min=16, max=16, message = "Le n° de carte bancaire est composé 16 chiffres")
    private String numCarte;

    @Column(name = "date_expiration")
    private Date dateCarte;

    @Column(name = "cryptogramme")
    //@Size(min=3, max=3, message = "Le cryptogramme est composé de 3 chiffres")
    private String cryptogramme;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTypeCarte() {
        return typeCarte;
    }

    public void setTypeCarte(String typeCarte) {
        this.typeCarte = typeCarte;
    }

    public String getNumCarte() {
        return numCarte;
    }

    public void setNumCarte(String numCarte) {
        this.numCarte = numCarte;
    }

    public Date getDateCarte() {
        return dateCarte;
    }

    public void setDateCarte(Date dateCarte) {
        this.dateCarte = dateCarte;
    }

    public String getCryptogramme() {
        return cryptogramme;
    }

    public void setCryptogramme(String cryptogramme) {
        this.cryptogramme = cryptogramme;
    }
}
