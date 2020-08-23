package fr.afcepf.algeek.dto.composant;

import fr.afcepf.algeek.dto.Produit;

public class Clavier extends ComposantOrdinateur {

    public static final String sans_filKey = "clavier_sans_fil";
    public static final String interface_pcKey = "clavier_interface_pc";
    public static final String connexionKey = "clavier_connexion";
    public static final String mecaniqueKey = "clavier_mecanique";

    public Clavier(Produit p) {
        super(p);
    }

    public Boolean isSansFil() {
        return (Boolean) getCaracteristique(sans_filKey);
    }

    public void setSansFil(Boolean value) {
        setCaracteristique(sans_filKey, value);
    }

    public String getInterfacePc() {
        return (String) getCaracteristique(interface_pcKey);
    }

    public void setInterfacePc(String value) {
        setCaracteristique(interface_pcKey, value);
    }

    public String getConnexion() {
        return (String) getCaracteristique(connexionKey);
    }

    public void setConnexion(String value) {
        setCaracteristique(connexionKey, value);
    }

    public Boolean isMecanique() {
        return (Boolean) getCaracteristique(mecaniqueKey);
    }

    public void setMecanique(Boolean value) {
        setCaracteristique(mecaniqueKey, value);
    }

    @Override
    public boolean estCompatibleAvec(ComposantOrdinateur autre) {
        // TODO Auto-generated method stub
        return true;
    }

}