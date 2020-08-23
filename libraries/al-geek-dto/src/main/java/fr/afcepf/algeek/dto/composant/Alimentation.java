package fr.afcepf.algeek.dto.composant;


import fr.afcepf.algeek.dto.Produit;

public class Alimentation extends ComposantOrdinateur {

    public static final String formatKey = "alim_format";
    public static final String puissanceKey = "alim_puissance";
    public static final String modulariteKey = "alim_modularite";
    public static final String certificationKey = "alim_certification";

    public Alimentation(Produit p) {
        super(p);
    }

    public String getFormat() {
        return (String) getCaracteristique(formatKey);
    }

    public void setFormat(String value) {
        setCaracteristique(formatKey, value);
    }

    public Integer getPuissance() {
        return (Integer) getCaracteristique(puissanceKey);
    }

    public void setPuissance(Integer value) {
        setCaracteristique(puissanceKey, value);
    }

    public String getModularite() {
        return (String) getCaracteristique(modulariteKey);
    }

    public void setModularite(String value) {
        setCaracteristique(modulariteKey, value);
    }

    public String getCertification() {
        return (String) getCaracteristique(certificationKey);
    }

    public void setCertification(String value) {
        setCaracteristique(certificationKey, value);
    }


    @Override
    public boolean estCompatibleAvec(ComposantOrdinateur autre) {
        if (autre instanceof Boitier) {
            Boitier boitier = (Boitier) autre;

            if (boitier.isCptAlimAtx() && getFormat().toUpperCase().contains("ATX"))
                return true;

            if (boitier.isCptAlimEps() && getFormat().toUpperCase().contains("EPS"))
                return true;

            if (boitier.isCptAlimPs2() &&
                    (getFormat().toUpperCase().contains("PS2") || getFormat().toUpperCase().contains("PS/2")))
                return true;

            return false;
        }

        return true;
    }

}