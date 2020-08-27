package fr.afcepf.algeek.converter;

import fr.afcepf.algeek.dto.TypeProduit;

public class TypeProduitConverter {

    public TypeProduit convertToDTO(fr.afcepf.algeek.entity.TypeProduit typeProduit) {
        TypeProduit dto = new TypeProduit();
        dto.setId(typeProduit.getId());
        dto.setNom(typeProduit.getNom());
        return dto;
    }

    public fr.afcepf.algeek.entity.TypeProduit convertToEntity (TypeProduit typeProduit) {
        fr.afcepf.algeek.entity.TypeProduit entity = new fr.afcepf.algeek.entity.TypeProduit();
        entity.setId(typeProduit.getId());
        entity.setNom(typeProduit.getNom());
        return entity;
    }
}
