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
    public Client getClientById(@PathVariable Long id){
        Client client = new ClientConverter().convertToDTO(clientDao.findById(id).get());
        return client;
    }

    @GetMapping(value = "/email={email}")
    public Client getByEmail(@PathVariable String email){
        Client client = new ClientConverter().convertToDTO(clientDao.findByEmail(email));
        return client;
    }

    @GetMapping(value = "/all")
    public List<Client> getAllClients(){
        List<fr.afcepf.algeek.entity.Client> clients = new ArrayList<>();
        clients = (List<fr.afcepf.algeek.entity.Client>) clientDao.findAll();

        List<Client> clientsDTO = new ArrayList<>();
        for (fr.afcepf.algeek.entity.Client c : clients){
            clientsDTO.add(clientConverter.convertToDTO(c));
        }
        return clientsDTO;
    }

    @PostMapping(value = "/add")
    public ResponseEntity<Client> ajouterClient(@RequestBody Client client){
        Client returnedClient = null;
        if (client.getId()==null){
            fr.afcepf.algeek.entity.Client addedEntity = clientDao.save(clientConverter.convertToEntity(client));
            returnedClient = clientConverter.convertToDTO(addedEntity);
            return new ResponseEntity<Client>(returnedClient, HttpStatus.OK);
        } else {
            return new ResponseEntity<Client>(returnedClient, HttpStatus.ALREADY_REPORTED);
        }
    }

    @DeleteMapping(value = "id={id}")
    public ResponseEntity<Client> supprimerClient(@PathVariable Long id){
        Client deletedClient = null;
        if (clientDao.findById(id).isPresent()){
            try {
                deletedClient = clientConverter.convertToDTO(clientDao.findById(id).get());
                clientDao.delete(clientDao.findById(id).get());
                return new ResponseEntity<Client>(deletedClient, HttpStatus.OK);
            } catch (Exception e){
                e.printStackTrace();
                return new ResponseEntity<Client>(HttpStatus.BAD_REQUEST);
            }
        } else {
            return new ResponseEntity<Client>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping(value = "/update")
    public ResponseEntity<Client> modifierClient(@RequestBody Client client){
        System.out.println("Dans la méthode modifierClient, début :");
        Client c = clientConverter.convertToEntity(client);
        System.out.println("Après la conversion en entité");
        try {
            System.out.println("Dans le try");
            c = clientDao.save(c);
            System.out.println("après le save");
            return new ResponseEntity<Client>(clientConverter.convertToDTO(c), HttpStatus.OK);
        } catch (Exception e){
            System.out.println("Dans l'exception");
            return new ResponseEntity<Client>(HttpStatus.BAD_REQUEST);
        }
    }


}
