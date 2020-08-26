package fr.afcepf.algeek.rest;


import fr.afcepf.algeek.dto.Client;
import fr.afcepf.algeek.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "")
public class ClientRestController {

    @Autowired
    private ClientService clientService;

    private String urlCustomerManager = "http://localhost:8180/customer/";


    @PutMapping(value = "/update")
    public ResponseEntity<Client> modifierClient(@RequestBody Client client) {
        Client clientModifie = clientService.modifier(client);
        if (clientModifie != null) {
            return new ResponseEntity<>(client, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(value = "add")
    public ResponseEntity<Client> ajouterClient(@RequestBody Client client) {
        Client addedClient = clientService.ajouter(client);
        if (addedClient != null) {
            return new ResponseEntity<>(addedClient, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.ALREADY_REPORTED);
        } //refaire avec le if
    }


    @DeleteMapping(value = "/id={id}")
    public ResponseEntity<Client> supprimerClient(@PathVariable Long id) {
        Client c = clientService.getById(id);
        try {
            clientService.supprimer(id);
            return new ResponseEntity<>(c, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "/id={id}")
    public ResponseEntity<Client> getClientById(@PathVariable Long id) {
        if (clientService.getById(id) != null) {
            return new ResponseEntity<>(clientService.getById(id), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "/email={email}/password={password}")
    public ResponseEntity<Client> doConnecter(@PathVariable String email, @PathVariable String password){
        if (clientService.doConnecter(email,password) != null){
            return new ResponseEntity<>(clientService.doConnecter(email, password), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "/all")
    public ResponseEntity<List<Client>> getAll() {
        if (clientService.getAll() != null) {
            return new ResponseEntity<>(clientService.getAll(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }


}
