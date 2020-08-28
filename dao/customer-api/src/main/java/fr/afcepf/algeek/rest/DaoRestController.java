package fr.afcepf.algeek.rest;

import fr.afcepf.algeek.converter.ClientConverter;
import fr.afcepf.algeek.dao.ClientDao;
import fr.afcepf.algeek.entity.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "", headers = "Accept=application/json")
public class DaoRestController {

    @Autowired
    private ClientDao clientDao;

    private final ClientConverter clientConverter = new ClientConverter();

    @GetMapping(value = "/id={id}")
    public fr.afcepf.algeek.dto.Client getClientById(@PathVariable Long id){
        fr.afcepf.algeek.dto.Client client = new ClientConverter().convertToDTO(clientDao.findById(id).get());
        return client;
    }

    @GetMapping(value = "/email={email}")
    public fr.afcepf.algeek.dto.Client getByEmail(@PathVariable String email){
        fr.afcepf.algeek.dto.Client client = new ClientConverter().convertToDTO(clientDao.findByEmail(email));
        return client;
    }

    @GetMapping(value = "/all")
    public List<fr.afcepf.algeek.dto.Client> getAllClients(){
        List<Client> clients = new ArrayList<>();
        clients = (List<Client>) clientDao.findAll();

        List<fr.afcepf.algeek.dto.Client> clientsDTO = new ArrayList<>();
        for (Client c : clients){
            clientsDTO.add(clientConverter.convertToDTO(c));
        }
        return clientsDTO;
    }

    @PostMapping(value = "/add")
    public ResponseEntity<fr.afcepf.algeek.dto.Client> ajouterClient(@RequestBody fr.afcepf.algeek.dto.Client client){
        fr.afcepf.algeek.dto.Client returnedClient = null;
        if (client.getId()==null){
            Client addedEntity = clientDao.save(clientConverter.convertToEntity(client));
            returnedClient = clientConverter.convertToDTO(addedEntity);
            return new ResponseEntity<>(returnedClient, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.ALREADY_REPORTED);
        }
    }

    @DeleteMapping(value = "id={id}")
    public ResponseEntity<fr.afcepf.algeek.dto.Client> supprimerClient(@PathVariable Long id){
        fr.afcepf.algeek.dto.Client deletedClient = null;
        if (clientDao.findById(id).isPresent()){
            try {
                deletedClient = clientConverter.convertToDTO(clientDao.findById(id).get());
                clientDao.delete(clientDao.findById(id).get());
                return new ResponseEntity<>(deletedClient, HttpStatus.OK);
            } catch (Exception e){
                e.printStackTrace();
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping(value = "/update")
    public ResponseEntity<fr.afcepf.algeek.dto.Client> modifierClient(@RequestBody fr.afcepf.algeek.dto.Client client){
        System.out.println("Dans la méthode modifierClient, début :");
        Client c = clientConverter.convertToEntity(client);
        System.out.println("Après la conversion en entité");
        try {
            System.out.println("Dans le try");
            c = clientDao.save(c);
            System.out.println("après le save");
            return new ResponseEntity<>(clientConverter.convertToDTO(c), HttpStatus.OK);
        } catch (Exception e){
            System.out.println("Dans l'exception");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }


}
