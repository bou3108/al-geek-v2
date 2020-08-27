package fr.afcepf.algeek.converter;

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

        commande.setInfosBank(
                new Converter().infosBancairesConverter.convertToDTO(
                        commandeEntity.getInfosBank()));

        List<LigneCommande> ligneCommandes = new ArrayList<>();
        for (LigneCommandeEntity ligneCommandeEntity : commandeEntity.getListLigneCommande()) {
            Converter converter = new Converter();
            ligneCommandes.add(converter.ligneCommandeConverter.convertToDTO(ligneCommandeEntity, commande));
        }
        commande.setListLigneCommande(ligneCommandes);

        return commande;
    }

    public CommandeEntity convertToEntity(Commande commande) {
        CommandeEntity commandeEntity = new CommandeEntity();

        commandeEntity.setId(commande.getId());
        commandeEntity.setRefClient(commande.getClientId());
        commandeEntity.setDateDeLaCommande(commande.getDateDeLaCommande());

        commandeEntity.setInfosBank(
                new Converter().infosBancairesConverter.convertToEntity(
                        commande.getInfosBank()));

        for (LigneCommande ligneCommande : commande.getListLigneCommande()) {
            commandeEntity.getListLigneCommande().add(
                    new Converter().ligneCommandeConverter.convertToEntity(ligneCommande)
            );
        }

        return commandeEntity;
    }

}
