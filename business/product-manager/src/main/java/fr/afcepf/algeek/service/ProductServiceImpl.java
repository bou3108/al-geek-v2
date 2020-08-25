package fr.afcepf.algeek.service;

import fr.afcepf.algeek.dto.Caracteristique;
import fr.afcepf.algeek.dto.Produit;
import fr.afcepf.algeek.dto.TypeProduit;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
public class ProductServiceImpl implements ProductService {

    private RestTemplate restTemplate = new RestTemplate();
    private String urlProductApi = "http://localhost:8080/db/product";
//    private String urlProductApi = "http://productapi-env.eba-fts62g3r.eu-west-3.elasticbeanstalk.com/db/product";


    @Override
    public Produit getProduitAvecCaracteristiques(Long id) {
        Produit produit = rechercherParId(id);
        String url = urlProductApi + "/carac/id=" + id;
        try {
            Caracteristique[] caracteristiquesArray = restTemplate.getForObject(url, Caracteristique[].class);
            List<Caracteristique> caracteristiques = Arrays.asList(caracteristiquesArray);
            produit.setCaracteristiques(caracteristiques);
            return produit;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Produit> getProduitParType(Long id, boolean chargerCaracteristiques) {
        String url = urlProductApi + "/type=" + id + "&with=" + chargerCaracteristiques;
        try {
            Produit[] produitsByTypeArray = restTemplate.getForObject(url, Produit[].class);
            List<Produit> produitsByType = Arrays.asList(produitsByTypeArray);
            return produitsByType;
        } catch (Exception e) {
            return null;
        }
    }

    // TO DO
    @Override
    public List<Produit> getNouveautes() {
        String url = urlProductApi + "/nouveautes";
        try {
            Produit[] products = restTemplate.getForObject(url, Produit[].class);
            List<Produit> nouveautes = Arrays.asList(products);
            return nouveautes;
        } catch (Exception e) {
            return  null;
        }
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
