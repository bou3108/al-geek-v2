package fr.afcepf.algeek.web.panier;

import fr.afcepf.algeek.dto.LigneCommande;
import fr.afcepf.algeek.dto.Produit;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class LignePanier {

    private LigneCommande ligneCommande = new LigneCommande();

    private Produit produit;


    public LignePanier(Produit produit) {
        this.produit = produit;
        this.ligneCommande.setProduitId(produit.getId());
        this.ligneCommande.setQuantite(produit.getQuantite());
    }


    public void setProduitId(Long produitId) {
        ligneCommande.setProduitId(produitId);
    }

    public Long getProduitId() {
        return ligneCommande.getProduitId();
    }


    public void setQuantite(Integer quantite) {
        ligneCommande.setQuantite(quantite);
    }

    public Integer getQuantite() {
        return ligneCommande.getQuantite();
    }


    public void setCommandeId(Long commandeId) {
        ligneCommande.setCommandeId(commandeId);
    }

    public Long getCommandeId() {
        return ligneCommande.getCommandeId();
    }


    public void incrementeQuantite() {
        incrementeQuantite(1);
    }

    public void incrementeQuantite(Integer quantite) {
        ligneCommande.setQuantite(ligneCommande.getQuantite() + quantite);
    }


    public void decrementeQuantite() {
        decrementeQuantite(1);
    }

    public void decrementeQuantite(Integer quantite) {
        ligneCommande.setQuantite(ligneCommande.getQuantite() - quantite);
    }


    public double getPrixLigne() {
        return produit.getPrix() * getQuantite();
    }

}
