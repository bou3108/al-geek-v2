package fr.afcepf.algeek.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class Produit implements Serializable {

    private Long id;
    private String nom;
    private String description;
    private String photo;
    private double prix;
    private TypeProduit type;
    private Marque marque;
    private List<Caracteristique> caracteristiques;

    private Date dateAjout;


    private int quantite = 1;

    //=================================================================

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

}
