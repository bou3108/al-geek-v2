package fr.afcepf.algeek.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity(name = "ligne_commande")
public class LigneCommandeEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    public LigneCommandeEntity() {
        super();
    }

    public LigneCommandeEntity(
            Long id,
            Integer quantite,
            Long refProduit) {

        super();
        this.id = id;
        this.quantite = quantite;
        this.refProduit = refProduit;
    }

    public LigneCommandeEntity(
            Long id,
            Integer quantite,
            Long refProduit,
            CommandeEntity commande) {

        super();
        this.id = id;
        this.quantite = quantite;
        this.refProduit = refProduit;
        this.commande = commande;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer quantite;

    private Long refProduit;

    @ManyToOne(fetch = FetchType.EAGER)
    private CommandeEntity commande;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getQuantite() {
        return quantite;
    }

    public void setQuantite(Integer quantite) {
        this.quantite = quantite;
    }

    public Long getRefProduit() {
        return refProduit;
    }

    public void setRefProduit(Long refProduit) {
        this.refProduit = refProduit;
    }

    public CommandeEntity getCommande() {
        return commande;
    }

    public void setCommande(CommandeEntity commandeEntity) {
        this.commande = commandeEntity;
    }
}
