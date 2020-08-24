package fr.afcepf.algeek.service;

import fr.afcepf.algeek.dto.Produit;
import fr.afcepf.algeek.dto.TypeProduit;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ProductServiceImpl implements ProductService {

    private RestTemplate restTemplate = new RestTemplate();
    private String urlProductApi = "http://localhost:8080/items";

    // TO DO
    @Override
    public Produit getProduitAvecCaracteristiques(Long id) {
        Produit produit = rechercherParId(id);
        String url = urlProductApi = "/carac/id=" + id;

        return restTemplate.getForObject(url, Produit.class);
    }

    @Override
    public Produit getProduitParType(Long id, boolean chargeCaracteristiques) {
        String url = urlProductApi + "/type=" + id;

        return restTemplate.getForObject(url, Produit.class);
    }

    // TO DO
    @Override
    public List<Produit> filtrerUsageOccasionnel(List<Produit> produits, String typeOrdi) {
        return null;
    }

    // TO DO
    @Override
    public List<Produit> filtrerUsageRegulier(List<Produit> produits, String typeOrdi) {
        return null;
    }

    // TO DO
    @Override
    public List<Produit> filtrerUsageIntensif(List<Produit> produits, String typeOrdi) {
        return null;
    }

    // TO DO
    @Override
    public List<Produit> trierMoinsCherAuPlusCher(Long idType) {
        return null;
    }

    // TO DO
    @Override
    public List<Produit> getNouveautes() {
        return null;
    }

    // TO DO
    @Override
    public List<TypeProduit> getTypesComposants() {
        return null;
    }

    // TO DO
    @Override
    public boolean estCompatibleAvec(Produit premier, Produit second) {
        return false;
    }

    @Override
    public Produit ajouter(Produit p) {
        String url = urlProductApi + "/add";
        try {
            return restTemplate.postForObject(url, p, Produit.class);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public boolean supprimer(Long id) {
        String url = urlProductApi + "/id=" + id;
        try {
            restTemplate.delete(url);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public Produit modifier(Produit p) {
        String url = urlProductApi + "/update";
        try {
            restTemplate.put(url,p);
            return p;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public Produit rechercherParId(Long id) {
        String url = urlProductApi + "/id=" + id;
        try {
            Produit produit = restTemplate.getForObject(url, Produit.class);
            return produit;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<Produit> getAll() {
        String url = urlProductApi + "/all";
        Produit[] products = restTemplate.getForObject(url, Produit[].class);
        List<Produit> allProducts = Arrays.asList(products);

        return allProducts;
    }
}
