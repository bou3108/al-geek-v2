package fr.afcepf.algeek.service;

import fr.afcepf.algeek.dto.Caracteristique;
import fr.afcepf.algeek.dto.Categorie;
import fr.afcepf.algeek.dto.Produit;
import fr.afcepf.algeek.dto.TypeProduit;
import fr.afcepf.algeek.rest.ResponseEntityRestCommunicator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestTemplate;

import java.io.Serializable;
import java.util.*;

/**
 * Classe d'implémentation de ProduitService permettant d'attaquer les WebServices de l'application  product-api
 * (url stockée en attribut)
 */
@Service
public class ProductServiceImpl implements ProductService {

    private RestTemplate restTemplate = new RestTemplate();
    private ResponseEntityRestCommunicator<Produit> produitResponseEntityRestCommunicator =
            new ResponseEntityRestCommunicator<>(Produit.class, Produit[].class);
    private ResponseEntityRestCommunicator<Caracteristique> caracteristiqueResponseEntityRestCommunicator =
            new ResponseEntityRestCommunicator<>(Caracteristique.class, Caracteristique[].class);
    private  ResponseEntityRestCommunicator<Categorie> categorieResponseEntityRestCommunicator =
            new ResponseEntityRestCommunicator<>(Categorie.class, Categorie[].class);


//    @Value("${algeek.db.product.address}")
//    private String urlProductApi;
    private String urlProductApi = "http://localhost:8080/db/product";
//    private String urlProductApi = "http://productapi-env.eba-fts62g3r.eu-west-3.elasticbeanstalk.com/db/product";


    /**
     * Retourne dans une ResponseEntity un Produit embarquant sa liste de Caractéristique en fonction de son id passé en paramètre
     * Retourne un code 400 en cas d'échec
     * @param id
     * @return
     */
    @Override
    public ResponseEntity<Produit> getProduitAvecCaracteristiques(Long id) {
        try {
            Produit produit = rechercherParId(id).getBody();
            String url = urlProductApi + "/carac/id=" + id;
            ResponseEntity<List<Caracteristique>> response = caracteristiqueResponseEntityRestCommunicator.getList(url);
            produit.setCaracteristiques(response.getBody());
            return new ResponseEntity<>(produit, response.getStatusCode());
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Retourne dans une ResponseEntity une liste de Produit du type passé en paramètre, avec ou sans la liste de Caracteristique
     * de chaque produit suivant le booléen passé en paramètre
     * @param id
     * @param chargerCaracteristiques
     * @return
     */
    @Override
    public ResponseEntity<List<Produit>> getProduitsParType(Long id, boolean chargerCaracteristiques) {
        String url = urlProductApi + "/type=" + id + "&with=" + chargerCaracteristiques;
        return produitResponseEntityRestCommunicator.getList(url, HttpStatus.NOT_FOUND);
    }

    /**
     * Retourne dans une ResponseEntity une liste des 30 derniers produits ajoutés en base.
     * Renvoie un code 400 si pas trouvé
     * @return
     */
    @Override
    public ResponseEntity<List<Produit>> getNouveautes() {
        String url = urlProductApi + "/nouveautes";
        return produitResponseEntityRestCommunicator.getList(url, HttpStatus.NOT_FOUND);
    }

    /**
     * Appelle la méthode ajouterProduit de l'application product-api
     * Retourne dans une ResponseEntity le produit ajouté en base si OK,
     * et un code 409 (CONFLICT) si le produit était déjà présent en base.
     * @param p
     * @return
     */
    @Override
    public ResponseEntity<Produit> ajouter(Produit p) {
        String url = urlProductApi + "/add";
        return produitResponseEntityRestCommunicator.post(url, p, HttpStatus.CONFLICT);
    }

    /**
     * Appelle la méthode supprimerProduit de l'application product-api
     * Retourne dans une ResponseEntity le booléen "true" si la suppression OK,
     * et un code 404 si NOK
     * @param id
     * @return
     */
    @Override
    public ResponseEntity<Produit> supprimer(Long id) {
        String url = urlProductApi + "/id=" + id;
        return produitResponseEntityRestCommunicator.delete(url, HttpStatus.NOT_FOUND);
    }

    /**
     * Appelle la méthode modifierProduit de l'application product-api
     * Retourne dans une ResponseEntity le Produit modifié si OK,
     * et un code 404 si NOK
     * @param p
     * @return
     */
    @Override
    public ResponseEntity<Produit> modifier(Produit p) {
        String url = urlProductApi + "/update";
        return produitResponseEntityRestCommunicator.put(url, p, HttpStatus.NOT_FOUND);
    }

    /**
     * Appelle la méthode getById de l'application product-api
     * Retourne dans une ResponseEntity le produit passé en param si trouvé,
     * et un code 404 si NOK
     * @param id
     * @return
     */
    @Override
    public ResponseEntity<Produit> rechercherParId(Long id) {
        String url = urlProductApi + "/id=" + id;
        return produitResponseEntityRestCommunicator.get(url, HttpStatus.NOT_FOUND);
    }

    /**
     * Appelle la méthode getAll dans l'application product-api
     * Retourne dans une ResponseEntity la liste de tous les produits si trouvés,
     * et un code 404 si NOK
     * @return
     */
    @Override
    public ResponseEntity<List<Produit>> getAll() {
        String url = urlProductApi + "/all";
        return produitResponseEntityRestCommunicator.getList(url, HttpStatus.NOT_FOUND);
    }

    @Override
    public ResponseEntity<Categorie> getRootCategorie() {
        String url = urlProductApi + "/root";
        return categorieResponseEntityRestCommunicator.get(url, HttpStatus.NOT_FOUND);
    }
}
