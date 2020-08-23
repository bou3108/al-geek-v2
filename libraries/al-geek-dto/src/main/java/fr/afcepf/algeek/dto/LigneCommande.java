package fr.afcepf.algeek.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class LigneCommande implements Serializable {

    private Long id;
    private Integer quantite;
    private Produit produit;
    private Commande commande;

    public LigneCommande(Long id, Integer quantite, Produit produit) {
        super();
        this.id = id;
        this.quantite = quantite;
        this.produit = produit;
    }

}
