package fr.afcepf.algeek.dao;

import fr.afcepf.algeek.entity.CommandeEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CommandeDao extends CrudRepository<CommandeEntity, Long> {
    List<CommandeEntity> findAllByRefClientOrderByDateDeLaCommandeDesc(Long refClient);
}
