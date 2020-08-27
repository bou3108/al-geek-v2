package fr.afcepf.algeek.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Commande implements Serializable {

    private Long id;
    private List<LigneCommande> listLigneCommande;
    private Date dateDeLaCommande;
    private Long clientId;
    private InfosBancaires infosBank;
    private double prix;

    public Commande(Long id, List<LigneCommande> listLigneCommande, Date dateDeLaCommande, double prix) {
        super();
        this.id = id;
        this.listLigneCommande = listLigneCommande;
        this.dateDeLaCommande = dateDeLaCommande;
        this.prix = prix;
    }


}
