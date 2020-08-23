package fr.afcepf.algeek.dto.composant;

import fr.afcepf.algeek.dto.Produit;

public class Processeur extends ComposantOrdinateur {

    public static final String socketKey = "proc_socket";
    public static final String ramKey = "proc_ram";
    public static final String max_ramKey = "proc_max_ram";

    public Processeur(Produit p) {
        super(p);
    }

    public String getSocket() {
        return (String) getCaracteristique(socketKey);
    }

    public void setSocket(String value) {
        setCaracteristique(socketKey, value);
    }

    public String getRam() {
        return (String) getCaracteristique(ramKey);
    }

    public void setRam(String value) {
        setCaracteristique(ramKey, value);
    }

    public Integer getMaxRam() {
        return (Integer) getCaracteristique(max_ramKey);
    }

    public void setMaxRam(Integer value) {
        setCaracteristique(max_ramKey, value);
    }

    public boolean isAmd() {
        return getSocket().toUpperCase().contains("AM4");
    }

    public boolean isIntel() {
        return getSocket().toUpperCase().contains("LGA1151") || getSocket().toUpperCase().contains("LGA 1151");
    }

    @Override
    public boolean estCompatibleAvec(ComposantOrdinateur autre) {
        if (autre instanceof CarteGraphique) {
            return autre.estCompatibleAvec(this);
        }

        if (autre instanceof CarteMere) {
            return autre.estCompatibleAvec(this);
        }

        if (autre instanceof CarteReseau) {
            return autre.estCompatibleAvec(this);
        }

        if (autre instanceof Ventirad) {
            return autre.estCompatibleAvec(this);
        }

        return true;
    }

}