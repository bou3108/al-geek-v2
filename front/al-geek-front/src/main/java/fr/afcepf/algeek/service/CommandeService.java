package fr.afcepf.algeek.service;

import fr.afcepf.algeek.dto.Commande;
import fr.afcepf.algeek.dto.InfosBancaires;
import fr.afcepf.algeek.dto.LigneCommande;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service
public class CommandeService {

    private static RestTemplate restTemplate = new RestTemplate();

    private String gatewayUrl = "http://ip:port/al-geek-gateway";

    // Remplace les appels à méthode des Services par un appel REST à order-manager
    public void ajouterInformationsBancaire(InfosBancaires infos) {
    }

    public void ajouterCommande(Commande commande) {
    }

    public void ajouterLigneDeCommande(LigneCommande ligneCommande) {

    }
}
