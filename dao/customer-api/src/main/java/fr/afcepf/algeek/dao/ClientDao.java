package fr.afcepf.algeek.dao;

import fr.afcepf.algeek.entity.Client;
import org.springframework.data.repository.CrudRepository;

public interface ClientDao extends CrudRepository<Client, Long> {

    public Client findByEmail(String email);


}
