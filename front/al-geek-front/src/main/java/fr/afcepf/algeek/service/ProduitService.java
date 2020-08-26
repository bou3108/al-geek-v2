package fr.afcepf.algeek.service;

import fr.afcepf.algeek.dto.Categorie;
import fr.afcepf.algeek.dto.Produit;
import fr.afcepf.algeek.dto.TypeProduit;
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
public class ProduitService {

    private final ResponseEntityRestCommunicator<Produit> productCommunicator = new ResponseEntityRestCommunicator<Produit>(Produit.class, Produit[].class);
    private final ResponseEntityRestCommunicator<Categorie> categoryCommunicator = new ResponseEntityRestCommunicator<Categorie>(Categorie.class, Categorie[].class);
    private final ResponseEntityRestCommunicator<TypeProduit> typeProduitCommunicator = new ResponseEntityRestCommunicator<TypeProduit>(TypeProduit.class, TypeProduit[].class);

    private final String gatewayUrl = "http://ip:port/gateway";

    // Remplace l'appel à getProduitAvecCaracteristiques de ProduitService par un appel REST à product-manager
    public Produit getProduitAvecCaracteristiques(Long productId) {
        String url = gatewayUrl + "/product/details/" + productId;
        ResponseEntity<Produit> response = productCommunicator.get(url);
        if (response.getStatusCode() == HttpStatus.OK) {
            return response.getBody();
        }
        return null;
    }

    // Remplace l'appel à CatalogueService par un appel à product-manager
    public List<Produit> getNouveautes() {
        String url = gatewayUrl + "/product/nouveautes";
        ResponseEntity<List<Produit>> response = productCommunicator.getList(url);
        if (response.getStatusCode() == HttpStatus.OK) {
            return response.getBody();
        }
        return null;
    }

    // Les méthodes ci-dessous remplacent l'appel à ProduitService par un appel REST au Product-Manager
    public List<Produit> trierMoinsCherAuPlusCher(Long typeProduitId) {
        String url = gatewayUrl + "/product/sort/price/ascending/" + typeProduitId;
        ResponseEntity<List<Produit>> response = productCommunicator.getList(url);
        if (response.getStatusCode() == HttpStatus.OK) {
            return response.getBody();
        }
        return null;
    }

    public List<Produit> filtrerUsageIntensif(List<Produit> listOrdi, String typeOrdi) {
        return null;
    }

    public List<Produit> filtrerUsageRegulier(List<Produit> listOrdi, String typeOrdi) {
        return null;
    }

    public List<Produit> filtrerUsageOccasionnel(List<Produit> listOrdi, String typeOrdi) {
        return null;
    }

    // Remplace l'appel à getProduitsParType de ProduitService par un appel REST à product-manager
    public List<Produit> getProduitsParType(Long id, boolean chargerCaracteristiques) {
        return null;
    }

    // Remplace l'appel à estCompatibleAvec de ConfigurateurService par un appel REST à product-manager
    public boolean estCompatibleAvec(Produit premier, Produit second) {
        return false;
    }

    // Remplace l'appel à estCompatibleAvec de ConfigurateurService par un appel REST à product-manager
    public List<TypeProduit> getTypesComposants() {
        String url = gatewayUrl + "product/types";
        ResponseEntity<List<TypeProduit>> response = typeProduitCommunicator.getList(url);
        if (response.getStatusCode() == HttpStatus.OK) {
            return response.getBody();
        }
        return null;
    }

    // Remplace l'appel à getRootCategorie de CategorieService par un appel REST à product-manager
    public Categorie getRootCategorie() {
        String url = gatewayUrl + "/product/root/";
        ResponseEntity<Categorie> response = categoryCommunicator.get(url);
        if (response.getStatusCode() == HttpStatus.OK) {
            return response.getBody();
        }
        return null;
    }
}
