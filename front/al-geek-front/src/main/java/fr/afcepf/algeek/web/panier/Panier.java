package fr.afcepf.algeek.web.panier;

import java.util.ArrayList;
import java.util.List;

import fr.afcepf.algeek.dto.Client;
import fr.afcepf.algeek.dto.LigneCommande;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Panier {

    private Client client;
    private List<LignePanier> listLignePanier;

    public Panier(Client client, List<LignePanier> listLignePanier) {
        super();
        this.client = client;
        this.listLignePanier = new ArrayList<>();
    }

    public List<LigneCommande> getListLigneCommande() {
        List<LigneCommande> ligneCommandeList = new ArrayList<>();
        for (LignePanier lp: listLignePanier) {
            ligneCommandeList.add(lp.getLigneCommande());
        }
        return ligneCommandeList;
    }

}
