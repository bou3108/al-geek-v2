package fr.afcepf.algeek.service;

import fr.afcepf.algeek.dto.Client;
import fr.afcepf.algeek.rest.ResponseEntityRestCommunicator;
import fr.afcepf.algeek.service.authentification.Authentification;
import fr.afcepf.algeek.service.authentification.Credentials;
import fr.afcepf.algeek.service.authentification.exception.AuthentificationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Slf4j
@Service
public class ClientServiceImpl implements ClientService {


    private RestTemplate restTemplate = new RestTemplate();

    private ResponseEntityRestCommunicator<Client> clientResponseEntityRestCommunicator
            = new ResponseEntityRestCommunicator<>(Client.class, Client[].class);

    @Value("${algeek.db.customer.address}")
    private String urlCustomerApi;
//    private String urlCustomerApi = "http://localhost:8080/db/customer";

    @Override
    public ResponseEntity<Client> ajouter(Client client) {
        String url = urlCustomerApi + "/add";
        return clientResponseEntityRestCommunicator.post(url, client, HttpStatus.CONFLICT);
    }

    @Override
    public ResponseEntity<Client> supprimer(Long id) {
        String url = urlCustomerApi + "/id=" + id;
        return clientResponseEntityRestCommunicator.delete(url, HttpStatus.NOT_FOUND);
    }

    @Override
    public ResponseEntity<Client> modifier(Client client) {
        String url = urlCustomerApi + "/update";
        return clientResponseEntityRestCommunicator.put(url, client, HttpStatus.NOT_FOUND);
    }

    @Override
    public ResponseEntity<Client> getById(Long id) {
        String url = urlCustomerApi + "/id=" + id;
        return clientResponseEntityRestCommunicator.get(url, HttpStatus.NOT_FOUND);
    }

    @Override
    public ResponseEntity<Client> doConnecter(String email, String password) {
        String url = urlCustomerApi + "/email=" + email;

        Client client = restTemplate.getForObject(url, Client.class);
        if (client == null) {
            return new ResponseEntity<Client>(HttpStatus.NOT_FOUND);
        }
        Credentials cred = new Credentials();
        cred.setSalt(client.getSalt());
        cred.setHashedPassword(client.getHashedPassword());
        cred.setLogin(client.getEmail());

        if (client != null) {
            try {
                log.info("email: " + email + " password :" + password);
                if (Authentification.authentificate(cred, password)) {

                    return new ResponseEntity<Client>(client, HttpStatus.OK);
                }
            } catch (AuthentificationException e) {
                log.error("Echec authentification", e);
            }
        }
        return new ResponseEntity<Client>(HttpStatus.CONFLICT);
    }

    @Override
    public ResponseEntity<List<Client>> getAll() {
        String url = urlCustomerApi + "/all";
        return clientResponseEntityRestCommunicator.getList(url, HttpStatus.NOT_FOUND);
    }

    @Override
    public ResponseEntity<Client> doRegister(Client client, String password) {
        String url = urlCustomerApi + "/add";
        Credentials cred = new Credentials();

        try {
            Authentification.initializeCredentials(cred, password);
            client.setSalt(cred.getSalt());
            client.setHashedPassword(cred.getHashedPassword());

        } catch (AuthentificationException e) {
            // Gérer erreur d'authentification (message erreur, log...)
            log.error("doRegister failure", e);
            return null;
        }
        return clientResponseEntityRestCommunicator.post(url, client);
    }

    @Override
    public ResponseEntity<Client> findByEmail(String email) {
        String url = urlCustomerApi + "/email=" + email;
        return clientResponseEntityRestCommunicator.get(url, HttpStatus.NOT_FOUND);
    }
}
