package fr.afcepf.algeek.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class Produit {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nom")
    private String nom;

    @Column(name = "description")
    private String description;

    @Column(name = "photo")
    private String photo;

    @Column(name = "prix")
    private double prix;

    @ManyToOne(fetch = FetchType.EAGER)
    private TypeProduit type;

    @ManyToOne(fetch = FetchType.EAGER)
    private Marque marque;

    @OneToMany(mappedBy = "produit", fetch = FetchType.LAZY)
    private List<Caracteristique> caracteristiques;

    @Getter
    @Setter
    @Column(name = "date_ajout")
    @Temporal(TemporalType.DATE)
    private Date dateAjout;

    @Transient
    private int quantite = 1;

    //=================================================================

    public Produit() {
    }

    public boolean hasAttribute(String key) {
        Caracteristique attr = getAttribute(key);
        return attr == null ? false : true;

    }

    public Caracteristique getAttribute(String key) {
        for (Caracteristique carac : caracteristiques)
            if (carac.getCle().contentEquals(key))
                return carac;

        return null;
    }

    public void setAttribute(String key, String value) {
        Caracteristique carac = getAttribute(key);
        if (carac == null) {
            caracteristiques.add(new Caracteristique(this, key, value));
        } else {
            carac.setValeurString(value);
        }
    }

    public void setAttribute(String key, int value) {
        Caracteristique carac = getAttribute(key);
        if (carac == null) {
            caracteristiques.add(new Caracteristique(this, key, value));
        } else {
            carac.setValeurInteger(value);
        }
    }

    public void setAttribute(String key, double value) {
        Caracteristique carac = getAttribute(key);
        if (carac == null) {
            caracteristiques.add(new Caracteristique(this, key, value));
        } else {
            carac.setValeurDouble(value);
        }
    }

    public void setAttribute(String key, boolean value) {
        Caracteristique carac = getAttribute(key);
        if (carac == null) {
            caracteristiques.add(new Caracteristique(this, key, value));
        } else {
            carac.setValeurBoolean(value);
        }
    }

    public String getStringAttribute(String key) {
        Caracteristique attr = getAttribute(key);

        if (attr == null)
            throw new RuntimeException("There's no attribute " + key);

        if (attr.getType() != Caracteristique.TypeCaracteristique.stringType)
            throw new RuntimeException("The attribute " + key + " is not a String.");

        return attr.getValeurString();
    }

    public int getIntAttribute(String key) {
        Caracteristique attr = getAttribute(key);

        if (attr == null)
            throw new RuntimeException("There's no attribute " + key);

        if (attr.getType() != Caracteristique.TypeCaracteristique.intType)
            throw new RuntimeException("The attribute " + key + " is not an integer.");

        return attr.getValeurInteger();
    }

    public double getDoubleAttribute(String key) {
        Caracteristique attr = getAttribute(key);

        if (attr == null)
            throw new RuntimeException("There's no attribute " + key);

        if (attr.getType() != Caracteristique.TypeCaracteristique.doubleType)
            throw new RuntimeException("The attribute " + key + " is not a double.");

        return attr.getValeurDouble();
    }

    public boolean getBoolAttribute(String key) {
        Caracteristique attr = getAttribute(key);

        if (attr == null)
            throw new RuntimeException("There's no attribute " + key);

        if (attr.getType() != Caracteristique.TypeCaracteristique.boolType)
            throw new RuntimeException("The attribute " + key + " is not a boolean.");

        return attr.getValeurBoolean();
    }

    //=================================================================

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Marque getMarque() {
        return marque;
    }

    public void setMarque(Marque marque) {
        this.marque = marque;
    }

    public List<Caracteristique> getCaracteristiques() {
        return caracteristiques;
    }

    public void setCaracteristiques(List<Caracteristique> caracteristiques) {
        this.caracteristiques = caracteristiques;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public TypeProduit getType() {
        return type;
    }

    public void setType(TypeProduit type) {
        this.type = type;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

}
