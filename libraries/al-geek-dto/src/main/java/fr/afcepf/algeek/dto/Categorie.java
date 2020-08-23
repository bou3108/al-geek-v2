package fr.afcepf.algeek.dto;

import java.util.List;

public class Categorie {

    private Long id;
    private Categorie parent;
    private List<Categorie> enfants;
    private String nomAffiche;
    private TypeProduit typeProduit;

    // ========================================================
    // Constructors

    public Categorie() {
        super();
    }

    public Categorie(Long id, Categorie parent, List<Categorie> enfants, String nomAffiche, TypeProduit typeProduit) {
        super();
        this.parent = parent;
        this.enfants = enfants;
        this.nomAffiche = nomAffiche;
        this.typeProduit = typeProduit;
    }


    // ========================================================
    // Getters/Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Categorie getParent() {
        return parent;
    }

    public void setParent(Categorie parent) {
        this.parent = parent;
    }

    public List<Categorie> getEnfants() {
        return enfants;
    }

    public void setEnfants(List<Categorie> enfants) {
        this.enfants = enfants;
    }

    public String getNomAffiche() {
        return nomAffiche;
    }

    public void setNomAffiche(String nomAffiche) {
        this.nomAffiche = nomAffiche;
    }

    public TypeProduit getTypeProduit() {
        return typeProduit;
    }

    public void setTypeProduit(TypeProduit typeProduit) {
        this.typeProduit = typeProduit;
    }

}
