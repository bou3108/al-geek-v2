package fr.afcepf.algeek.converter;

import fr.afcepf.algeek.dao.CommandeDao;
import fr.afcepf.algeek.dto.Commande;
import fr.afcepf.algeek.dto.LigneCommande;
import fr.afcepf.algeek.entity.CommandeEntity;
import fr.afcepf.algeek.entity.LigneCommandeEntity;
import org.springframework.beans.factory.annotation.Autowired;

public class LigneCommandeConverter {

    @Autowired
    private CommandeDao commandeDao;

    public LigneCommande convertToDTO(LigneCommandeEntity ligneCommandeEntity) {
        LigneCommande ligneCommande = new LigneCommande(
                ligneCommandeEntity.getId(),
                ligneCommandeEntity.getQuantite(),
                ligneCommandeEntity.getRefProduit());
        ligneCommande.setCommandeId(ligneCommandeEntity.getCommande().getId());

        return ligneCommande;
    }

    public LigneCommandeEntity convertToEntity(LigneCommande ligneCommande) {
        LigneCommandeEntity ligneCommandeEntity = new LigneCommandeEntity(
                ligneCommande.getId(),
                ligneCommande.getQuantite(),
                ligneCommande.getProduitId());

        CommandeEntity commandeEntity = commandeDao.findById(ligneCommande.getCommandeId()).get();
        ligneCommandeEntity.setCommande(commandeEntity);
        return ligneCommandeEntity;
    }

}
