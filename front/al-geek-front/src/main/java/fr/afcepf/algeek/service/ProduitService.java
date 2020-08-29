package fr.afcepf.algeek.service;

import fr.afcepf.algeek.dto.Categorie;
import fr.afcepf.algeek.dto.Produit;
import fr.afcepf.algeek.dto.TypeProduit;
import fr.afcepf.algeek.rest.ResponseEntityRestCommunicator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class ProduitService {

    @Value("${algeek.gateway.address}")
    private String gatewayUrl;

    private final ResponseEntityRestCommunicator<Produit> productCommunicator = new ResponseEntityRestCommunicator<Produit>(Produit.class, Produit[].class);
    private final ResponseEntityRestCommunicator<Categorie> categoryCommunicator = new ResponseEntityRestCommunicator<Categorie>(Categorie.class, Categorie[].class);
    private final ResponseEntityRestCommunicator<TypeProduit> typeProduitCommunicator = new ResponseEntityRestCommunicator<TypeProduit>(TypeProduit.class, TypeProduit[].class);
    private final ResponseEntityRestCommunicator<Produit[]> confCommunicator = new ResponseEntityRestCommunicator<Produit[]>(Produit[].class, Produit[][].class);


    // Remplace l'appel à getProduitAvecCaracteristiques de ProduitService par un appel REST à product-manager
    public Produit getProduitAvecCaracteristiques(Long productId) {
        String url = gatewayUrl + "/product/details/id=" + productId;
        ResponseEntity<Produit> response = productCommunicator.get(url);
        if (response.getStatusCode() == HttpStatus.OK) {
            return response.getBody();
        }
        return null;
    }

    // Remplace l'appel à CatalogueService par un appel à product-manager
    public List<Produit> getNouveautes() {
        String url = gatewayUrl + "/product/latest";
        ResponseEntity<List<Produit>> response = productCommunicator.getList(url);
        if (response.getStatusCode() == HttpStatus.OK) {
            return response.getBody();
        }
        return new ArrayList<>();
    }

    // Les méthodes ci-dessous remplacent l'appel à ProduitService par un appel REST au Product-Manager
    public List<Produit> trierMoinsCherAuPlusCher(Long typeProduitId) {
        String url = gatewayUrl + "/product/filter/sort/price/ascending/id=" + typeProduitId;
        ResponseEntity<List<Produit>> response = productCommunicator.getList(url);
        if (response.getStatusCode() == HttpStatus.OK) {
            return response.getBody();
        }
        return new ArrayList<>();
    }

    public List<Produit> filtrerUsageIntensif(List<Produit> listOrdi, String typeOrdi) {
        String url = gatewayUrl + "/product/filter/use/intensive/kind=" + typeOrdi;
        ResponseEntity<List<Produit>> response = productCommunicator.postList(url, listOrdi);
        if (response.getStatusCode() == HttpStatus.OK) {
            return response.getBody();
        }
        return new ArrayList<>();
    }

    public List<Produit> filtrerUsageRegulier(List<Produit> listOrdi, String typeOrdi) {
        String url = gatewayUrl + "/product/filter/use/regular/kind=" + typeOrdi;
        ResponseEntity<List<Produit>> response = productCommunicator.postList(url, listOrdi);
        if (response.getStatusCode() == HttpStatus.OK) {
            return response.getBody();
        }
        return new ArrayList<>();
    }

    public List<Produit> filtrerUsageOccasionnel(List<Produit> listOrdi, String typeOrdi) {
        String url = gatewayUrl + "/product/filter/use/casual/kind=" + typeOrdi;
        ResponseEntity<List<Produit>> response = productCommunicator.postList(url, listOrdi);
        if (response.getStatusCode() == HttpStatus.OK) {
            return response.getBody();
        }
        return new ArrayList<>();
    }

    // Remplace l'appel à getProduitsParType de ProduitService par un appel REST à product-manager
    public List<Produit> getProduitsParType(Long id, boolean chargerCaracteristiques) {
        String url = gatewayUrl + "/product/type=" + id + "&with=" + chargerCaracteristiques;
        ResponseEntity<List<Produit>> response = productCommunicator.getList(url);
        if (response.getStatusCode() == HttpStatus.OK) {
            return response.getBody();
        }
        return new ArrayList<>();
    }

    // Remplace l'appel à estCompatibleAvec de ConfigurateurService par un appel REST à product-manager
    public Boolean estCompatibleAvec(Produit premier, Produit second) {
        String url = gatewayUrl + "/product/configure/compatibility";
        Produit[] products = new Produit[]{premier, second};
        ResponseEntity<Produit[]> response = confCommunicator.post(url, products);
        if (response.getStatusCode() == HttpStatus.OK) {
            return true;
        }
        return false;
    }

    // Remplace l'appel à estCompatibleAvec de ConfigurateurService par un appel REST à product-manager
    public List<TypeProduit> getTypesComposants() {
        String url = gatewayUrl + "/product/configure/types/all";
        ResponseEntity<List<TypeProduit>> response = typeProduitCommunicator.getList(url);
        if (response.getStatusCode() == HttpStatus.OK) {
            return response.getBody();
        }
        return new ArrayList<>();
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
