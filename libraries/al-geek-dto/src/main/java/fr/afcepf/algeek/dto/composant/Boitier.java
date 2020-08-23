package fr.afcepf.algeek.dto.composant;


import fr.afcepf.algeek.dto.Produit;

public class Boitier extends ComposantOrdinateur {

    public static final String cpt_alim_atxKey = "boitier_cpt_alim_atx";
    public static final String cpt_alim_epsKey = "boitier_cpt_alim_eps";
    public static final String cpt_alim_ps2Key = "boitier_cpt_alim_ps2";
    public static final String cpt_cm_atxKey = "boitier_cpt_cm_atx";
    public static final String cpt_cm_microatxKey = "boitier_cpt_cm_microatx";
    public static final String cpt_cm_miniitxKey = "boitier_cpt_cm_miniitx";
    public static final String cpt_cm_eatxKey = "boitier_cpt_cm_eatx";

    public Boitier(Produit p) {
        super(p);
    }

    public Boolean isCptAlimAtx() {
        return (Boolean) getCaracteristique(cpt_alim_atxKey);
    }

    public void setCptAlimAtx(Boolean value) {
        setCaracteristique(cpt_alim_atxKey, value);
    }

    public Boolean isCptAlimEps() {
        return (Boolean) getCaracteristique(cpt_alim_epsKey);
    }

    public void setCptAlimEps(Boolean value) {
        setCaracteristique(cpt_alim_epsKey, value);
    }

    public Boolean isCptAlimPs2() {
        return (Boolean) getCaracteristique(cpt_alim_ps2Key);
    }

    public void setCptAlimPs2(Boolean value) {
        setCaracteristique(cpt_alim_ps2Key, value);
    }

    public Boolean isCptCmAtx() {
        return (Boolean) getCaracteristique(cpt_cm_atxKey);
    }

    public void setCptCmAtx(Boolean value) {
        setCaracteristique(cpt_cm_atxKey, value);
    }

    public Boolean isCptCmMicroatx() {
        return (Boolean) getCaracteristique(cpt_cm_microatxKey);
    }

    public void setCptCmMicroatx(Boolean value) {
        setCaracteristique(cpt_cm_microatxKey, value);
    }

    public Boolean isCptCmMiniitx() {
        return (Boolean) getCaracteristique(cpt_cm_miniitxKey);
    }

    public void setCptCmMiniitx(Boolean value) {
        setCaracteristique(cpt_cm_miniitxKey, value);
    }

    public Boolean isCptCmEatx() {
        return (Boolean) getCaracteristique(cpt_cm_eatxKey);
    }

    public void setCptCmEatx(Boolean value) {
        setCaracteristique(cpt_cm_eatxKey, value);
    }

    @Override
    public boolean estCompatibleAvec(ComposantOrdinateur autre) {
        if (autre instanceof CarteMere) {
            CarteMere cm = (CarteMere) autre;

            if (cm.getFormFactor().toUpperCase().equals("ATX") && isCptCmAtx())
                return true;

            if (cm.getFormFactor().toUpperCase().equals("Micro-ATX") && isCptCmMicroatx())
                return true;

            return false;
        }

        if (autre instanceof Alimentation) {
            return autre.estCompatibleAvec(this);
        }

        return true;
    }

}