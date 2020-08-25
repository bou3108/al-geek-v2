package fr.afcepf.algeek.service;

import fr.afcepf.algeek.dto.Produit;
import fr.afcepf.algeek.dto.TypeProduit;

import java.util.List;

public interface ProductService {

    Produit getProduitAvecCaracteristiques(Long id);

    List<Produit> getProduitParType (Long id, boolean chargerCaracteristiques);

    List<Produit> getNouveautes();

    List<TypeProduit> getTypesComposants();

    boolean estCompatibleAvec(Produit premier, Produit second);

    Produit ajouter (Produit p);

    boolean supprimer (Long id);

    Produit modifier (Produit p);

    Produit rechercherParId(Long id);

    List<Produit> getAll();
}
