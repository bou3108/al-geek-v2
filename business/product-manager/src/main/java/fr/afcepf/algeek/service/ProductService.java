package fr.afcepf.algeek.service;

import fr.afcepf.algeek.dto.Categorie;
import fr.afcepf.algeek.dto.Produit;
import fr.afcepf.algeek.dto.TypeProduit;
import org.springframework.http.ResponseEntity;

import java.io.Serializable;
import java.util.List;

public interface ProductService {

    ResponseEntity<Produit> getProduitAvecCaracteristiques(Long id);

    ResponseEntity<List<Produit>> getProduitsParType (Long id, boolean chargerCaracteristiques);

    ResponseEntity<List<Produit>> getNouveautes();

    ResponseEntity<Produit> ajouter (Produit p);

    ResponseEntity<Produit> supprimer (Long id);

    ResponseEntity<Produit> modifier (Produit p);

    ResponseEntity<Produit> rechercherParId (Long id);

    ResponseEntity<List<Produit>> getAll();

    ResponseEntity<Categorie> getRootCategorie();
}
