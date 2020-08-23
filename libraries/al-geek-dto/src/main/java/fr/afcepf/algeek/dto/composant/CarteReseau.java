package fr.afcepf.algeek.dto.composant;

import fr.afcepf.algeek.dto.Produit;

public class CarteReseau extends ComposantOrdinateur {

    public static final String cpt_amdKey = "cr_cpt_amd";
    public static final String cpt_intelKey = "cr_cpt_intel";

    public CarteReseau(Produit p) {
        super(p);
    }

    public Boolean isCptAmd() {
        return (Boolean) getCaracteristique(cpt_amdKey);
    }

    public void setCptAmd(Boolean value) {
        setCaracteristique(cpt_amdKey, value);
    }

    public Boolean isCptIntel() {
        return (Boolean) getCaracteristique(cpt_intelKey);
    }

    public void setCptIntel(Boolean value) {
        setCaracteristique(cpt_intelKey, value);
    }

    @Override
    public boolean estCompatibleAvec(ComposantOrdinateur autre) {
        if (autre instanceof Processeur) {
            Processeur proc = (Processeur) autre;

            if (isCptAmd() && proc.isAmd())
                return true;

            if (isCptIntel() && proc.isIntel())
                return true;

            return false;
        }

        return true;
    }
}