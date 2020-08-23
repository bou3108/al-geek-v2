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
    private List<LigneCommande> listLC;

    public Panier(Client client, List<LigneCommande> listLC) {
        super();
        this.client = client;
        this.listLC = new ArrayList<LigneCommande>();
    }

}
