package fr.afcepf.algeek.service;

import fr.afcepf.algeek.dto.Produit;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Slf4j
@Service
public class VenteService {

    private static RestTemplate restTemplate= new RestTemplate();

    private String gatewayUrl = "http://ip:port/al-geek-gateway";

    // Remplace l'appel à CatalogueService par un appel à sales-orchestrator
    public List<Produit> getMeilleuresVentes(int size) {
        List<Produit> products = new ArrayList<>();
        try {
            Produit[] bestSalesProducts = restTemplate.getForObject(gatewayUrl + "/sale/best-sales/ + size", Produit[].class);
            products = Arrays.asList(bestSalesProducts);
        } catch (Exception ex) {
            log.error("getMeilleuresVentes : " + ex.getMessage() , ex);
        }
        return products;
    }
}
