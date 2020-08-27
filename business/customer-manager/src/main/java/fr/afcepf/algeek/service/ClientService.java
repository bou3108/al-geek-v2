package fr.afcepf.algeek.service;


import fr.afcepf.algeek.dto.Client;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ClientService {

	ResponseEntity<Client> ajouter(Client client);

	ResponseEntity<Client> supprimer(Long id);

	ResponseEntity<Client> modifier(Client client);

	ResponseEntity<Client> getById(Long id);

	ResponseEntity<Client> doConnecter(String email, String password);

	ResponseEntity<List<Client>> getAll();

	ResponseEntity<Client> doRegister(Client client, String password);

	ResponseEntity<Client> findByEmail(String email);
}
