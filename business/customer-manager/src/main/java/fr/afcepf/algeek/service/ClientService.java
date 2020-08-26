package fr.afcepf.algeek.service;


import fr.afcepf.algeek.dto.Client;

import java.util.List;

public interface ClientService {

	Client ajouter(Client client);

	boolean supprimer(Long id);

	Client modifier(Client client);

	Client getById(Long id);

	Client doConnecter(String email, String password);

	List<Client> getAll();



}
