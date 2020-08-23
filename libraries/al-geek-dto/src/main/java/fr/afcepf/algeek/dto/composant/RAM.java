package fr.afcepf.algeek.dto.composant;

import fr.afcepf.algeek.dto.Produit;

public class RAM extends ComposantOrdinateur {

    public static final String capaciteKey = "ram_capacite";
    public static final String typeKey = "ram_type";

    public RAM(Produit p) {
        super(p);
    }

    public Integer getCapacite() {
        return (Integer) getCaracteristique(capaciteKey);
    }

    public void setCapacite(Integer value) {
        setCaracteristique(capaciteKey, value);
    }

    public String getType() {
        return (String) getCaracteristique(typeKey);
    }

    public void setType(String value) {
        setCaracteristique(typeKey, value);
    }

    @Override
    public boolean estCompatibleAvec(ComposantOrdinateur autre) {
        if (autre instanceof CarteMere)
            return autre.estCompatibleAvec(this);
        return true;
    }

}