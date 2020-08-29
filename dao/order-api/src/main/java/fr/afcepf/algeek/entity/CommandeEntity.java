package fr.afcepf.algeek.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity(name = "commande")
public class CommandeEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    public CommandeEntity() {
        super();
    }

    public CommandeEntity(
            Long id,
            List<LigneCommandeEntity> listLigneCommande,
            Date dateDeLaCommande,
            double prix) {

        super();
        this.id = id;
        this.listLigneCommande = listLigneCommande;
        this.dateDeLaCommande = dateDeLaCommande;
        this.prix = prix;
    }

    public CommandeEntity(
            Long id,
            List<LigneCommandeEntity> listLigneCommande,
            Date dateDeLaCommande,
            Long refClient,
            InfosBancairesEntity infosBank,
            double prix) {

        super();
        this.id = id;
        this.listLigneCommande = listLigneCommande;
        this.dateDeLaCommande = dateDeLaCommande;
        this.refClient = refClient;
        this.infosBank = infosBank;
        this.prix = prix;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "commande", fetch = FetchType.EAGER)
    @Cascade(CascadeType.PERSIST)
    private List<LigneCommandeEntity> listLigneCommande;

    @Column(name = "date_commande")
    @Temporal(TemporalType.DATE)
    private Date dateDeLaCommande;

    private Long refClient;

    @OneToOne
    @Cascade(CascadeType.PERSIST)
    private InfosBancairesEntity infosBank;

    private double prix;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<LigneCommandeEntity> getListLigneCommande() {
        return listLigneCommande;
    }

    public void setListLigneCommande(List<LigneCommandeEntity> listLigneCommande) {
        this.listLigneCommande = listLigneCommande;
    }

    public Date getDateDeLaCommande() {
        return dateDeLaCommande;
    }

    public void setDateDeLaCommande(Date dateDeLaCommande) {
        this.dateDeLaCommande = dateDeLaCommande;
    }

    public Long getRefClient() {
        return refClient;
    }

    public void setRefClient(Long refClient) {
        this.refClient = refClient;
    }

    public InfosBancairesEntity getInfosBank() {
        return infosBank;
    }

    public void setInfosBank(InfosBancairesEntity infosBank) {
        this.infosBank = infosBank;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }
}
