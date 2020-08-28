package fr.afcepf.algeek.converter;

import fr.afcepf.algeek.dao.CommandeDao;
import fr.afcepf.algeek.dto.Commande;
import fr.afcepf.algeek.dto.LigneCommande;
import fr.afcepf.algeek.entity.CommandeEntity;
import fr.afcepf.algeek.entity.LigneCommandeEntity;
import org.springframework.stereotype.Service;

@Service
public class LigneCommandeConverter {

    public LigneCommande convertToDTO(LigneCommandeEntity ligneCommandeEntity) {
        LigneCommande ligneCommande = new LigneCommande(
                ligneCommandeEntity.getId(),
                ligneCommandeEntity.getQuantite(),
                ligneCommandeEntity.getRefProduit());

        CommandeEntity commandeEntity = ligneCommandeEntity.getCommande();
        Long id = commandeEntity.getId();
        ligneCommande.setCommandeId(id);

        return ligneCommande;
    }

    public LigneCommandeEntity convertToEntity(LigneCommande ligneCommande, CommandeDao commandeDao) {
        LigneCommandeEntity ligneCommandeEntity = new LigneCommandeEntity(
                ligneCommande.getId(),
                ligneCommande.getQuantite(),
                ligneCommande.getProduitId());

        CommandeEntity commandeEntity = commandeDao.findById(ligneCommande.getCommandeId()).get();
        ligneCommandeEntity.setCommande(commandeEntity);
        return ligneCommandeEntity;
    }

}
