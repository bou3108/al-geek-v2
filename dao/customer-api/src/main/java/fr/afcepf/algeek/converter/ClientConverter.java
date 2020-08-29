package fr.afcepf.algeek.converter;

import fr.afcepf.algeek.entity.Client;

public class ClientConverter {

    public fr.afcepf.algeek.dto.Client convertToDTO(Client client){
        fr.afcepf.algeek.dto.Client dto = new fr.afcepf.algeek.dto.Client();

        dto.setId(client.getId());
        dto.setNom(client.getNom());
        dto.setPrenom(client.getPrenom());
        dto.setAdresse(client.getAdresse());
        dto.setCodePostal(client.getCodePostal());
        dto.setVille(client.getVille());
        dto.setLogin(client.getLogin());
        dto.setEmail(client.getEmail());
        dto.setHashedPassword(client.getHashedPassword());
        dto.setSalt(client.getSalt());

        return dto;
    }



    public Client convertToEntity(fr.afcepf.algeek.dto.Client client){
        Client entity = new Client();

        entity.setId(client.getId());
        entity.setNom(client.getNom());
        entity.setPrenom(client.getPrenom());
        entity.setAdresse(client.getAdresse());
        entity.setCodePostal(client.getCodePostal());
        entity.setVille(client.getVille());
        entity.setLogin(client.getLogin());
        entity.setEmail(client.getEmail());
        entity.setHashedPassword(client.getHashedPassword());
        entity.setSalt(client.getSalt());

        return entity;
    }

}
