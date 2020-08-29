package fr.afcepf.algeek.service;

import fr.afcepf.algeek.dto.Produit;
import fr.afcepf.algeek.util.TriPrixCroissant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe d'implémentation de FilterService permettant de filtrer des listes de Produit par prix ou par usage
 */
@Slf4j
@Component
public class FilterServiceImpl implements FilterService {

//    @Autowired
//    private ProductService productService;
    private ProductService productService = new ProductServiceImpl();

    /**
     * retourne une liste de produits correspondant à un usage occasionnel à partir d'une liste passée en paramètre
     * @param produits
     * @param typeOrdi
     * @return
     */
    @Override
    public List<Produit> filtrerUsageOccasionnel(List<Produit> produits, String typeOrdi) {
        List<Produit> listeFiltree = new ArrayList<>();
        for (Produit p : produits) {
            if (p.getStringAttribute(typeOrdi).equals("bureautique")) {
                listeFiltree.add(p);
            }
        }
        return listeFiltree;
    }

    /**
     * retourne une liste de produits correspondant à un usage régulier à partir d'une liste passée en paramètre
     * @param produits
     * @param typeOrdi
     * @return
     */
    @Override
    public List<Produit> filtrerUsageRegulier(List<Produit> produits, String typeOrdi) {
        List<Produit> listeFiltree = new ArrayList<>();
        for (Produit p : produits) {
            if (p.getStringAttribute(typeOrdi).equals("multimedia")) {
                listeFiltree.add(p);
            }
        }
        return listeFiltree;
    }

    /**
     * retourne une liste de produits correspondant à un usage intensif à partir d'une liste passée en paramètre
     * @param produits
     * @param typeOrdi
     * @return
     */
    @Override
    public List<Produit> filtrerUsageIntensif(List<Produit> produits, String typeOrdi) {
        List<Produit> listeFiltree = new ArrayList<>();
        for (Produit p : produits) {
            if (p.getStringAttribute(typeOrdi).equals("gaming")) {
                listeFiltree.add(p);
            }
        }
        return listeFiltree;
    }


}
