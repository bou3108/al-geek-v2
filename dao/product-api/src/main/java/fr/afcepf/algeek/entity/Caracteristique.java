package fr.afcepf.algeek.entity;

import javax.persistence.*;
import java.io.Serializable;


@Entity
public class Caracteristique implements Serializable {

    private static final long serialVersionUID = 1L;

    public enum TypeCaracteristique {
        stringType, doubleType, intType, boolType, undefined
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String cle;

    private String unite;

    @Enumerated(EnumType.STRING)
    private TypeCaracteristique type;

    @Column(name = "valeur_string")
    private String valeurString;

    @Column(name = "valeur_double")
    private Double valeurDouble;

    @Column(name = "valeur_integer")
    private Integer valeurInteger;

    @Column(name = "valeur_boolean")
    private Boolean valeurBoolean;

    @ManyToOne
    private Produit produit;

    // ===================================================

    public Caracteristique() {
        super();
        setType(TypeCaracteristique.undefined);
    }

    public Caracteristique(Produit p, TypeCaracteristique t) {
        setProduit(p);
        setType(t);
    }

    public Caracteristique(Produit p, String name, double value) {
        this(p, TypeCaracteristique.doubleType);
        setCle(name);
        setValeurDouble(value);
    }

    public Caracteristique(Produit p, String name, int value) {
        this(p, TypeCaracteristique.intType);
        setCle(name);
        setValeurInteger(value);
    }

    public Caracteristique(Produit p, String name, String value) {
        this(p, TypeCaracteristique.stringType);
        setCle(name);
        setValeurString(value);
    }

    public Caracteristique(Produit p, String name, boolean value) {
        this(p, TypeCaracteristique.boolType);
        setCle(name);
        setValeurBoolean(value);
    }

    // ===================================================

    public Object getValeurAuto() {
        switch (type) {
            case boolType:
                return getValeurBoolean();
            case doubleType:
                return getValeurDouble();
            case intType:
                return getValeurInteger();
            case stringType:
                String s = getValeurString();
                return s == null ? "" : s;
            case undefined:
            default:
                return null;
        }
    }

    public void setValeurAuto(Object val) {

        switch (type) {
            case boolType:
                setValeurBoolean((Boolean) val);
                break;
            case doubleType:
                setValeurDouble((Double) val);
                break;
            case intType:
                setValeurInteger((Integer) val);
                break;
            case stringType:
                setValeurString((String) val);
                break;
            case undefined:
            default:
                return;
        }
    }

    public String getCleAffichage() {
        // TODO passer par une classe intermédiaire qui fournit le texte final
        return cle;
    }

    public String getValeurAffichage() {
        switch (type) {
            case stringType:
                return ajoutUnite(valeurString);
            case intType:
                return valeurInteger == null ? "" : ajoutUnite(valeurInteger.toString());
            case doubleType:
                return valeurDouble == null ? "" : ajoutUnite(valeurDouble.toString());
            case boolType:
                return valeurBoolean ? "Oui" : "Non";
            default:
                return "";
        }
    }

    private String ajoutUnite(String value) {
        // Ajoute l'unité après la valeur, si l'unité n'est pas nulle
        boolean nullOrEmpty = unite == null || unite.isEmpty();
        return nullOrEmpty ? value : value + " " + unite;
    }

    // ===================================================

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCle() {
        return cle;
    }

    public void setCle(String cle) {
        this.cle = cle;
    }

    public TypeCaracteristique getType() {
        return type;
    }

    public void setType(TypeCaracteristique type) {
        this.type = type;
    }

    public String getValeurString() {
        return valeurString;
    }

    public void setValeurString(String valeurString) {
        this.valeurString = valeurString;
    }

    public Double getValeurDouble() {
        return valeurDouble;
    }

    public void setValeurDouble(Double valeurDouble) {
        this.valeurDouble = valeurDouble;
    }

    public Integer getValeurInteger() {
        return valeurInteger;
    }

    public void setValeurInteger(Integer valeurInteger) {
        this.valeurInteger = valeurInteger;
    }

    public Boolean getValeurBoolean() {
        return valeurBoolean;
    }

    public void setValeurBoolean(Boolean valeurBoolean) {
        this.valeurBoolean = valeurBoolean;
    }

    public Produit getProduit() {
        return produit;
    }

    public void setProduit(Produit produit) {
        this.produit = produit;
    }

    public String getUnite() {
        return unite;
    }

    public void setUnite(String unite) {
        this.unite = unite;
    }

}
