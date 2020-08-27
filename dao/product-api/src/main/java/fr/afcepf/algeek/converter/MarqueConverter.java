package fr.afcepf.algeek.converter;

import fr.afcepf.algeek.dto.Marque;

public class MarqueConverter {

    public Marque convertToDTO (fr.afcepf.algeek.entity.Marque marque) {
        Marque dto = new Marque();
        dto.setId(marque.getId());
        dto.setNom(marque.getNom());
        return dto;
    }

    public fr.afcepf.algeek.entity.Marque convertToEntity(Marque marque) {
        fr.afcepf.algeek.entity.Marque entity = new fr.afcepf.algeek.entity.Marque();
        entity.setId(marque.getId());
        entity.setNom(marque.getNom());
        return entity;
    }
}
