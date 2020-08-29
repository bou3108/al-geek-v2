package fr.afcepf.algeek.dao;

import fr.afcepf.algeek.entity.CommandeEntity;
import fr.afcepf.algeek.entity.LigneCommandeEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Repository
public interface LigneCommandeDao extends CrudRepository<LigneCommandeEntity, Long> {
    List<LigneCommandeEntity> getAllByCommande(CommandeEntity commandeEntity);
}
