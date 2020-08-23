package fr.afcepf.algeek.dto.composant;

import fr.afcepf.algeek.dto.Produit;

public class SSD extends ComposantOrdinateur {

    public static final String capaciteKey = "ssd_capacite";

    public SSD(Produit p) {
        super(p);
    }

    public Integer getCapacite() {
        return (Integer) getCaracteristique(capaciteKey);
    }

    public void setCapacite(Integer value) {
        setCaracteristique(capaciteKey, value);
    }

    @Override
    public boolean estCompatibleAvec(ComposantOrdinateur autre) {
        // TODO Auto-generated method stub
        return true;
    }
}