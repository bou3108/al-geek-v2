package fr.afcepf.algeek.service;

import fr.afcepf.algeek.dto.Client;
import fr.afcepf.algeek.dto.Commande;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Slf4j
@Service
public class ClientService {

    private static RestTemplate restTemplate = new RestTemplate();

    private String gatewayUrl = "http://ip:port/al-geek-gateway";

    // Remplace l'appel à getCommandesPourClient de CommandeService par un appel REST à customer-manager
    public List<Commande> getCommandesPourClient(Long clientId) {
        ArrayList<Commande> commandes = new ArrayList<>();
        try {
            Commande[] cmd = restTemplate.getForObject(gatewayUrl + "/order/customer/" + clientId, Commande[].class);
            commandes = (ArrayList<Commande>) Arrays.asList(cmd);
        } catch (Exception ex) {
            log.error("getCommandesPourClient : " + ex.getMessage() , ex);
        }
        return commandes;
    }

    // Remplace l'appel à modifier de ClientService par un appel REST à customer-manager
    public void modifierClient(Client client) {
        try {
            restTemplate.put(gatewayUrl + "/customer", client);
        } catch (Exception ex) {
            log.error("modifierClient : " + ex.getMessage() , ex);
        }
    }

    // Remplace l'appel à doConnecter de ClientService par un appel REST à customer-manager
    public Client connect(String email, String password) {
        return restTemplate.getForObject(gatewayUrl + "/customer/connect", Client.class, email, password);
    }

    public boolean doInscription(Client client, String password) {
        // TODO: Déplacer le code ci-dessous dans les web services business

//		Credentials cred = new Credentials();
//
//		try {
//			Authentification.initializeCredentials(cred, password);
//			cl.setSalt(cred.getSalt());
//			cl.setHashedPassword(cred.getHashedPassword());
//			if (cldao.findByEmail(cl.getEmail()) == null) {
//				cldao.save(cl);
//			}
//			else {
//				System.out.println("CETTE EMAIL EXISTE DEJA");
//			}
//		} catch (AuthentificationException e) {
//			return false;
//		}
//      return true

        return false;
    }

}
