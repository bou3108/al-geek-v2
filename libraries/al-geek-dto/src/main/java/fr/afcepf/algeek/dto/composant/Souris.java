package fr.afcepf.algeek.dto.composant;

import fr.afcepf.algeek.dto.Produit;

public class Souris extends ComposantOrdinateur {

    public static final String sans_filKey = "souris_sans_fil";
    public static final String interface_pcKey = "souris_interface_pc";
    public static final String connexionKey = "souris_connexion";
    public static final String nb_btnKey = "souris_nb_btn";

    public Souris(Produit p) {
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

    public String getNbBtn() {
        return (String) getCaracteristique(nb_btnKey);
    }

    public void setNbBtn(String value) {
        setCaracteristique(nb_btnKey, value);
    }

    @Override
    public boolean estCompatibleAvec(ComposantOrdinateur autre) {
        // TODO Auto-generated method stub
        return true;
    }

}