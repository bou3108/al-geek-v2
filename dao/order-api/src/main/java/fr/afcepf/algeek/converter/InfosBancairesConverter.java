package fr.afcepf.algeek.converter;

import fr.afcepf.algeek.dto.InfosBancaires;
import fr.afcepf.algeek.entity.InfosBancairesEntity;

public class InfosBancairesConverter {

    public InfosBancaires convertToDTO(InfosBancairesEntity infosBancairesEntity) {
        InfosBancaires infosBancaires = new InfosBancaires();

        infosBancaires.setId(infosBancairesEntity.getId());
        infosBancaires.setTypeCarte(infosBancairesEntity.getTypeCarte());
        infosBancaires.setNumCarte(infosBancairesEntity.getNumCarte());
        infosBancaires.setDateCarte(infosBancairesEntity.getDateCarte());
        infosBancaires.setCryptogramme(infosBancairesEntity.getCryptogramme());

        return infosBancaires;
    }

    public InfosBancairesEntity convertToEntity(InfosBancaires infosBancaires) {
        InfosBancairesEntity infosBancairesEntity = new InfosBancairesEntity();

        infosBancairesEntity.setId(infosBancaires.getId());
        infosBancairesEntity.setTypeCarte(infosBancaires.getTypeCarte());
        infosBancairesEntity.setNumCarte(infosBancaires.getNumCarte());
        infosBancairesEntity.setDateCarte(infosBancaires.getDateCarte());
        infosBancairesEntity.setCryptogramme(infosBancaires.getCryptogramme());

        return infosBancairesEntity;
    }
}
