package fr.afcepf.algeek.converter;

import fr.afcepf.algeek.dto.Categorie;

import java.util.ArrayList;
import java.util.List;

public class CategorieConverter {

    public Categorie convertToDTO (fr.afcepf.algeek.entity.Categorie categorie) {
        Categorie dto = new Categorie();
        dto.setId(categorie.getId());
        dto.setNomAffiche(categorie.getNomAffiche());
        dto.setTypeProduit(new TypeProduitConverter().convertToDTO(categorie.getTypeProduit()));
        dto.setParent(convertToDTO(categorie.getParent()));

        List<Categorie> enfantsDTO = new ArrayList<>();
        for (fr.afcepf.algeek.entity.Categorie c : categorie.getEnfants()) {
            enfantsDTO.add(convertToDTO(c));
        }
        dto.setEnfants(enfantsDTO);

        return dto;
    }

    public fr.afcepf.algeek.entity.Categorie convertToEntity (Categorie categorie) {
        fr.afcepf.algeek.entity.Categorie entity = new fr.afcepf.algeek.entity.Categorie();
        entity.setId(categorie.getId());
        entity.setNomAffiche(categorie.getNomAffiche());
        entity.setTypeProduit(new TypeProduitConverter().convertToEntity(categorie.getTypeProduit()));
        entity.setParent(convertToEntity(categorie.getParent()));

        List<fr.afcepf.algeek.entity.Categorie> enfantsEntity = new ArrayList<>();
        for (Categorie c : categorie.getEnfants()) {
            enfantsEntity.add(convertToEntity(c));
        }
        entity.setEnfants(enfantsEntity);

        return entity;
    }
}
