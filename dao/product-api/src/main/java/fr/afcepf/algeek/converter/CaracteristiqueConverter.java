package fr.afcepf.algeek.converter;

import fr.afcepf.algeek.dto.Caracteristique;

public class CaracteristiqueConverter {

    public Caracteristique convertToDTO(fr.afcepf.algeek.entity.Caracteristique caracteristique) {
        Caracteristique dto = new Caracteristique();
        dto.setId(caracteristique.getId());
        dto.setCle(caracteristique.getCle());
        dto.setProduit(new ProduitConverter().convertToDTO(caracteristique.getProduit()));
        fr.afcepf.algeek.entity.Caracteristique c =new fr.afcepf.algeek.entity.Caracteristique();
        caracteristique.getType();
        dto.setType(convertEntityTypeCaracteristique(caracteristique.getType()));
        dto.setUnite(caracteristique.getUnite());
        dto.setValeurAuto(caracteristique.getValeurAuto());

        return dto;
    }

    public fr.afcepf.algeek.entity.Caracteristique convertToEntity (Caracteristique caracteristique) {
        fr.afcepf.algeek.entity.Caracteristique entity = new fr.afcepf.algeek.entity.Caracteristique();
        entity.setId(caracteristique.getId());
        entity.setCle(caracteristique.getCle());
        entity.setProduit(new ProduitConverter().convertToEntity(caracteristique.getProduit()));
        entity.setType(convertDTOTypeCaracteristique(caracteristique.getType()));
        entity.setUnite(caracteristique.getUnite());
        entity.setValeurAuto(caracteristique.getValeurAuto());

        return entity;
    }

    private Caracteristique.TypeCaracteristique convertEntityTypeCaracteristique(fr.afcepf.algeek.entity.Caracteristique.TypeCaracteristique entityType) {
        Caracteristique.TypeCaracteristique dtoType;
        switch (entityType) {
            case stringType :
                dtoType = Caracteristique.TypeCaracteristique.stringType;
                break;
            case intType:
                dtoType = Caracteristique.TypeCaracteristique.intType;
                break;
            case doubleType:
                dtoType = Caracteristique.TypeCaracteristique.doubleType;
                break;
            case boolType:
                dtoType = Caracteristique.TypeCaracteristique.boolType;
                break;
            case undefined:
                dtoType = Caracteristique.TypeCaracteristique.undefined;
                break;
            default:
                dtoType = null;
                break;
        }
        return dtoType;
    }

    private fr.afcepf.algeek.entity.Caracteristique.TypeCaracteristique convertDTOTypeCaracteristique(Caracteristique.TypeCaracteristique dtoType) {
        fr.afcepf.algeek.entity.Caracteristique.TypeCaracteristique entityTpe;
        switch (dtoType) {
            case stringType:
                entityTpe = fr.afcepf.algeek.entity.Caracteristique.TypeCaracteristique.stringType;
                break;
            case intType:
                entityTpe = fr.afcepf.algeek.entity.Caracteristique.TypeCaracteristique.intType;
                break;
            case doubleType:
                entityTpe = fr.afcepf.algeek.entity.Caracteristique.TypeCaracteristique.doubleType;
                break;
            case boolType:
                entityTpe = fr.afcepf.algeek.entity.Caracteristique.TypeCaracteristique.boolType;
                break;
            case undefined:
                entityTpe = fr.afcepf.algeek.entity.Caracteristique.TypeCaracteristique.undefined;
                break;
            default:
                entityTpe = null;
                break;
        }
        return entityTpe;
    }
}
