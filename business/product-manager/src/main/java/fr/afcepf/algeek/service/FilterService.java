package fr.afcepf.algeek.service;

import fr.afcepf.algeek.dto.Produit;

import java.util.List;

public interface FilterService {

    List<Produit> filtrerUsageOccasionnel (List<Produit> produits, String typeOrdi);

    List<Produit> filtrerUsageRegulier (List<Produit> produits, String typeOrdi);

    List<Produit> filtrerUsageIntensif (List<Produit> produits, String typeOrdi);

    List<Produit> trierMoinsCherAuPlusCher (Long idType);
}
