package fr.afcepf.algeek.converter;

import fr.afcepf.algeek.dao.CommandeDao;
import fr.afcepf.algeek.dto.Commande;
import fr.afcepf.algeek.dto.LigneCommande;
import fr.afcepf.algeek.entity.CommandeEntity;
import fr.afcepf.algeek.entity.LigneCommandeEntity;

import java.util.ArrayList;
import java.util.List;

public class CommandeConverter {

    public Commande convertToDTO(CommandeEntity commandeEntity) {
        Commande commande = new Commande();

        commande.setId(commandeEntity.getId());
        commande.setClientId(commandeEntity.getRefClient());
        commande.setDateDeLaCommande(commandeEntity.getDateDeLaCommande());
        commande.setPrix(commandeEntity.getPrix());

        Converter converter = new Converter();

        commande.setInfosBank(
                converter.infosBancairesConverter.convertToDTO(
                        commandeEntity.getInfosBank()));

        List<LigneCommande> ligneCommandes = new ArrayList<>();
        for (LigneCommandeEntity ligneCommandeEntity : commandeEntity.getListLigneCommande()) {
            ligneCommandes.add(converter.ligneCommandeConverter.convertToDTO(ligneCommandeEntity));
        }
        commande.setListLigneCommande(ligneCommandes);

        return commande;
    }

    public CommandeEntity convertToEntity(Commande commande, CommandeDao commandeDao) {
        CommandeEntity commandeEntity = new CommandeEntity();

        commandeEntity.setId(commande.getId());
        commandeEntity.setRefClient(commande.getClientId());
        commandeEntity.setDateDeLaCommande(commande.getDateDeLaCommande());
        commandeEntity.setPrix(commande.getPrix());

        Converter converter = new Converter();
        commandeEntity.setInfosBank(
                converter.infosBancairesConverter.convertToEntity(
                        commande.getInfosBank()));

        commandeEntity.setListLigneCommande(new ArrayList<>());
        for (LigneCommande ligneCommande : commande.getListLigneCommande()) {
            commandeEntity.getListLigneCommande().add(
                    converter.ligneCommandeConverter.convertToEntity(ligneCommande, commandeDao)
            );
        }

        return commandeEntity;
    }

}
