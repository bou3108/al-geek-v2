package fr.afcepf.algeek.service;

import fr.afcepf.algeek.dto.Categorie;
import fr.afcepf.algeek.dto.Produit;
import fr.afcepf.algeek.dto.TypeProduit;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Slf4j
@Service
public class ProduitService {

    private static RestTemplate restTemplate= new RestTemplate();

    private String gatewayUrl = "http://ip:port/al-geek-gateway";

    // Remplace l'appel à getProduitAvecCaracteristiques de ProduitService par un appel REST à product-manager
    public Produit getProduitAvecCaracteristiques(Long productId) {
        try {
            return restTemplate.getForObject(gatewayUrl + "/product/details/" + productId, Produit.class);
        } catch (Exception ex) {
            log.error("getProduitAvecCaracteristiques : " +ex.getMessage(), ex);
        }
        return null;
    }

    // Remplace l'appel à CatalogueService par un appel à product-manager
    public List<Produit> getNouveautes() {
        List<Produit> products = new ArrayList<>();
        try {
            Produit[] latestProducts = restTemplate.getForObject(gatewayUrl + "/product/new", Produit[].class);
            products = Arrays.asList(latestProducts);
        } catch (Exception ex) {
            log.error("getNouveautes : " + ex.getMessage() , ex);
        }
        return products;
    }

    // Les méthodes ci-dessous remplacent l'appel à ProduitService par un appel REST au Product-Manager
    public List<Produit> trierMoinsCherAuPlusCher(Long typeProduitId) {
        List<Produit> products = new ArrayList<>();
        try {
            Produit[] latestProducts = restTemplate.getForObject(gatewayUrl + "/product/sort/price/ascending/" + typeProduitId, Produit[].class);
            products = Arrays.asList(latestProducts);
        } catch (Exception ex) {
            log.error("trierMoinsCherAuPlusCher : " + ex.getMessage() , ex);
        }
        return products;
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
        return null;
    }

    // Remplace l'appel à getRootCategorie de CategorieService par un appel REST à product-manager
    public Categorie getRootCategorie() {
        return null;
    }
}
