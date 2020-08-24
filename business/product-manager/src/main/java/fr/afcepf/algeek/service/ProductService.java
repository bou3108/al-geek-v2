package fr.afcepf.algeek.service;

import fr.afcepf.algeek.dto.Produit;
import fr.afcepf.algeek.dto.TypeProduit;

import java.util.List;

public interface ProductService {

    Produit getProduitAvecCaracteristiques(Long id);

    List<Produit> getProduitParType (Long id, boolean chargerCaracteristiques);

    List<Produit> filtrerUsageOccasionnel (List<Produit> produits, String typeOrdi);

    List<Produit> filtrerUsageRegulier (List<Produit> produits, String typeOrdi);

    List<Produit> filtrerUsageIntensif (List<Produit> produits, String typeOrdi);

    List<Produit> trierMoinsCherAuPlusCher (Long idType);

    List<Produit> getNouveautes();

    List<TypeProduit> getTypesComposants();

    boolean estCompatibleAvec(Produit premier, Produit second);

    Produit ajouter (Produit p);

    boolean supprimer (Long id);

    Produit modifier (Produit p);

    Produit rechercherParId(Long id);

    List<Produit> getAll();
}
