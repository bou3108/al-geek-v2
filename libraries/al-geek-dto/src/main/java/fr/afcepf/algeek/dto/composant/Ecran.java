package fr.afcepf.algeek.dto.composant;

import fr.afcepf.algeek.dto.Produit;

public class Ecran extends ComposantOrdinateur {

    public static final String tailleKey = "ecran_taille";
    public static final String res_maxKey = "ecran_res_max";
    public static final String type_dalleKey = "ecran_type_dalle";
    public static final String nb_hdmiKey = "ecran_nb_hdmi";
    public static final String incurveKey = "ecran_incurve";

    public Ecran(Produit p) {
        super(p);
    }

    public Double getTaille() {
        return (Double) getCaracteristique(tailleKey);
    }

    public void setTaille(Double value) {
        setCaracteristique(tailleKey, value);
    }

    public String getResMax() {
        return (String) getCaracteristique(res_maxKey);
    }

    public void setResMax(String value) {
        setCaracteristique(res_maxKey, value);
    }

    public String getTypeDalle() {
        return (String) getCaracteristique(type_dalleKey);
    }

    public void setTypeDalle(String value) {
        setCaracteristique(type_dalleKey, value);
    }

    public Integer getNbHdmi() {
        return (Integer) getCaracteristique(nb_hdmiKey);
    }

    public void setNbHdmi(Integer value) {
        setCaracteristique(nb_hdmiKey, value);
    }

    public Boolean isIncurve() {
        return (Boolean) getCaracteristique(incurveKey);
    }

    public void setIncurve(Boolean value) {
        setCaracteristique(incurveKey, value);
    }

    @Override
    public boolean estCompatibleAvec(ComposantOrdinateur autre) {
        // TODO Auto-generated method stub
        return true;
    }
}