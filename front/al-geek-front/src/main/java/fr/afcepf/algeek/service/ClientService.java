package fr.afcepf.algeek.service;

import fr.afcepf.algeek.dto.Client;
import fr.afcepf.algeek.dto.Commande;
import fr.afcepf.algeek.dto.LigneCommande;
import fr.afcepf.algeek.rest.ResponseEntityRestCommunicator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Slf4j
@Service
public class ClientService {

    private final ResponseEntityRestCommunicator<Commande> cmdCommunicator = new ResponseEntityRestCommunicator<Commande>(Commande.class, Commande[].class);
    private final ResponseEntityRestCommunicator<Client> customerCommunicator = new ResponseEntityRestCommunicator<Client>(Client.class, Client[].class);

    private String gatewayUrl = "http://ip:port/al-geek-gateway";

    // Remplace l'appel à getCommandesPourClient de CommandeService par un appel REST à customer-manager
    public List<Commande> getCommandesPourClient(Long clientId) {
        String url = gatewayUrl + "/order/customer/id=" + clientId;
        ResponseEntity<List<Commande>> response = cmdCommunicator.getList(url);
        if (response.getStatusCode() == HttpStatus.OK) {
            return response.getBody();
        }
        return null;
    }

    // Remplace l'appel à modifier de ClientService par un appel REST à customer-manager
    public Client modifierClient(Client client) {
        String url = gatewayUrl + "/customer/update";
        ResponseEntity<Client> response = customerCommunicator.put(url, client);
        if (response.getStatusCode() == HttpStatus.OK) {
            return response.getBody();
        }
        return null;
    }

    // Remplace l'appel à doConnecter de ClientService par un appel REST à customer-manager
    public Client connect(String email, String password) {
        String url = gatewayUrl + "/customer/authentication/email=" + email + "&password=" + password;
        ResponseEntity<Client> response = customerCommunicator.get(url);
        if (response.getStatusCode() == HttpStatus.OK) {
            return response.getBody();
        }
        return null;
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
