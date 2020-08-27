package fr.afcepf.algeek.dao;

import fr.afcepf.algeek.entity.LigneCommandeEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

@Service
public interface LigneCommandeDao extends CrudRepository<LigneCommandeEntity, Long> {
}
