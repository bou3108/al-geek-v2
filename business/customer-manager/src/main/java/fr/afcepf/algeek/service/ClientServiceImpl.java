package fr.afcepf.algeek.service;

import fr.afcepf.algeek.dto.Client;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class ClientServiceImpl implements ClientService {


    private RestTemplate restTemplate = new RestTemplate();
    private String urlCustomerApi = "http://localhost:8080/db/customer/";


    @Override
    public Client ajouter(Client client) {
        String url = urlCustomerApi + "/add";
        try {
            return restTemplate.postForObject(url, client, Client.class);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public boolean supprimer(Long id) {
        String url = urlCustomerApi + "/id=" + id;
        try {
            restTemplate.delete(url);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public Client modifier(Client client) {
        String url = urlCustomerApi + "/update";
        try {
            restTemplate.put(url, client);
            return client;
        }catch (Exception e) {
            return null;
        }
    }

    @Override
    public Client getById(Long id) {
        String url = urlCustomerApi + "/id=" +id;
        try {
            Client client = restTemplate.getForObject(url, Client.class);
            return client;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<Client> getAll() {
        String url = urlCustomerApi + "/all";
        Client[] clients = restTemplate.getForObject(url, Client[].class);
        List<Client> allClients = Arrays.asList(clients);

        return allClients;
    }
}
