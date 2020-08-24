package fr.afcepf.algeek.rest;

import fr.afcepf.algeek.converter.ClientConverter;
import fr.afcepf.algeek.dao.ClientDao;
import fr.afcepf.algeek.entity.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController(value="/client")
public class DaoRestController {

    @Autowired
    private ClientDao clientDao;

    private final ClientConverter clientConverter = new ClientConverter();

    @GetMapping(value = "/client/id={id}")
    public Client getClientById(@PathVariable Long id){
        Client client = new ClientConverter().convertToDTO(clientDao.findById(id).get());

        return client;
    }

    @GetMapping(value = "/clients")
    public List<Client> getAllClients(){
        List<fr.afcepf.algeek.entity.Client> clients = new ArrayList<>();
        clients = (List<fr.afcepf.algeek.entity.Client>) clientDao.findAll();

        List<Client> clientsDTO = new ArrayList<>();
        for (fr.afcepf.algeek.entity.Client c : clients){
            clientsDTO.add(clientConverter.convertToDTO(c));
        }
        return clientsDTO;
    }


}
