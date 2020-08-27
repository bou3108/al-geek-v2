package fr.afcepf.algeek.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
public class Categorie implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "parent")
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    private Categorie parent;

    @OneToMany(mappedBy = "parent", fetch = FetchType.EAGER)
    private List<Categorie> enfants;

    @Column(name = "nom_affiche")
    private String nomAffiche;

    @ManyToOne
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
