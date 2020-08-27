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

    @PutMapping(value = "/update")
    public ResponseEntity<Client> modifierClient(@RequestBody Client client) {
        return clientService.modifier(client);
    }

    @PostMapping(value = "/add")
    public ResponseEntity<Client> ajouterClient(@RequestBody Client client) {
        return clientService.ajouter(client);
    }

    @DeleteMapping(value = "/id={id}")
    public ResponseEntity<Client> supprimerClient(@PathVariable Long id) {
        return clientService.supprimer(id);
    }

    @GetMapping(value = "/id={id}")
    public ResponseEntity<Client> getClientById(@PathVariable Long id) {
        return clientService.getById(id);
    }


    @GetMapping(value = "/authentication/email={email}&password={password}")
    public ResponseEntity<Client> doConnecter(@PathVariable String email, @PathVariable String password) {
        return clientService.doConnecter(email, password);
    }

    @GetMapping(value = "/all")
    public ResponseEntity<List<Client>> getAll() {
        return clientService.getAll();
    }


    @PostMapping(value = "/register/password={password}")
    public ResponseEntity<Client> doRegister(@RequestBody Client client, @PathVariable String password) {
        if (clientService.findByEmail(client.getEmail()).getStatusCode() == HttpStatus.OK) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        return clientService.doRegister(client, password);
    }


}
