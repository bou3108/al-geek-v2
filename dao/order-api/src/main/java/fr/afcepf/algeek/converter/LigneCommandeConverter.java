package fr.afcepf.algeek.converter;

import fr.afcepf.algeek.dao.CommandeDao;
import fr.afcepf.algeek.dto.Commande;
import fr.afcepf.algeek.dto.LigneCommande;
import fr.afcepf.algeek.entity.LigneCommandeEntity;
import org.springframework.beans.factory.annotation.Autowired;

public class LigneCommandeConverter {

    @Autowired
    private CommandeDao commandeDao;

    public LigneCommande convertToDTO(LigneCommandeEntity ligneCommandeEntity, Commande commande) {
        LigneCommande ligneCommande = new LigneCommande(
                ligneCommandeEntity.getId(),
                ligneCommandeEntity.getQuantite(),
                ligneCommandeEntity.getRefProduit());
        ligneCommande.setCommandeId(commande.getId());

        return ligneCommande;
    }

    public LigneCommandeEntity convertToEntity(LigneCommande ligneCommande) {
        LigneCommandeEntity ligneCommandeEntity = new LigneCommandeEntity(
                ligneCommande.getId(),
                ligneCommande.getQuantite(),
                ligneCommande.getProduitId());

        ligneCommandeEntity.setCommande(commandeDao.findById(ligneCommande.getCommandeId()).get());
        return ligneCommandeEntity;
    }

}
