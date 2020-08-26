package fr.afcepf.algeek.service;

import fr.afcepf.algeek.dto.Categorie;
import fr.afcepf.algeek.dto.Produit;
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
public class VenteService {

    private final ResponseEntityRestCommunicator<Produit> productCommunicator = new ResponseEntityRestCommunicator<Produit>(Produit.class, Produit[].class);

    private final String gatewayUrl = "http://ip:port/gateway";

    // Remplace l'appel à CatalogueService par un appel à sales-orchestrator
    public List<Produit> getMeilleuresVentes(int size) {
        String url = gatewayUrl + "/sales/best-sales/" + size;
        ResponseEntity<List<Produit>> response = productCommunicator.getList(url);
        if (response.getStatusCode() == HttpStatus.OK) {
            return response.getBody();
        }
        return null;
    }
}
