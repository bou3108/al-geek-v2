package fr.afcepf.algeek.dto.composant;


import fr.afcepf.algeek.dto.Caracteristique;
import fr.afcepf.algeek.dto.Produit;

public abstract class ComposantOrdinateur {

    private Produit produit;

    protected ComposantOrdinateur(Produit p) {
        produit = p;
    }

    public Object getCaracteristique(String cle) {
        Caracteristique c = produit.getAttribute(cle);

        return c == null ? null : c.getValeurAuto();
    }

    public void setCaracteristique(String cle, Object val) {
        Caracteristique c = produit.getAttribute(cle);

        if (c != null)
            c.setValeurAuto(val);
    }

    public Produit getProduit() {
        return produit;
    }

    public void setProduit(Produit produit) {
        this.produit = produit;
    }

    public abstract boolean estCompatibleAvec(ComposantOrdinateur autre);

}