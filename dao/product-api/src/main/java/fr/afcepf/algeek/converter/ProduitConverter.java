package fr.afcepf.algeek.converter;

import fr.afcepf.algeek.dto.Produit;
import fr.afcepf.algeek.dto.TypeProduit;

public class ProduitConverter {

    public Produit convertToDTO (fr.afcepf.algeek.entity.Produit produit) {
        Produit dto = new Produit();
        dto.setId(produit.getId());
        dto.setNom(produit.getNom());
        dto.setDescription(produit.getDescription());
        dto.setPhoto(produit.getPhoto());
        dto.setPrix(produit.getPrix());
        dto.setType(new TypeProduitConverter().convertToDTO(produit.getType()));
        dto.setMarque(new MarqueConverter().convertToDTO(produit.getMarque()));
//        dto.setCaracteristiques(new Carac);
        dto.setDateAjout(produit.getDateAjout());

        return dto;
    }

    public fr.afcepf.algeek.entity.Produit convertToEntity(Produit produit) {
        fr.afcepf.algeek.entity.Produit entity = new fr.afcepf.algeek.entity.Produit();
        entity.setId(produit.getId());
        entity.setNom(produit.getNom());
        entity.setDescription(produit.getDescription());
        entity.setPhoto(produit.getPhoto());
        entity.setPrix(produit.getPrix());
        entity.setType(new TypeProduitConverter().convertToEntity(produit.getType()));
        entity.setMarque(new MarqueConverter().convertToEntity(produit.getMarque()));
        // entity.setCaracteristiques()
        entity.setDateAjout(produit.getDateAjout());

        return entity;
    }
}
