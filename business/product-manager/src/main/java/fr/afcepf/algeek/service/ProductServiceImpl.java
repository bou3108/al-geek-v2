package fr.afcepf.algeek.service;

import fr.afcepf.algeek.dto.Produit;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Override
    public List<Produit> filtrerUsageOccasionnel(List<Produit> produits, String typeOrdi) {
        return null;
    }

    @Override
    public List<Produit> filtrerUsageRegulier(List<Produit> produits, String typeOrdi) {
        return null;
    }

    @Override
    public List<Produit> filtrerUsageIntensif(List<Produit> produits, String typeOrdi) {
        return null;
    }
}
