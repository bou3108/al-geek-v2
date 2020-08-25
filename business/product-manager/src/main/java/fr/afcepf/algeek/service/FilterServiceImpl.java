package fr.afcepf.algeek.service;

import fr.afcepf.algeek.dto.Produit;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FilterServiceImpl implements FilterService {
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

    // TO DO
    @Override
    public List<Produit> trierMoinsCherAuPlusCher(Long idType) {
        return null;
    }


}
