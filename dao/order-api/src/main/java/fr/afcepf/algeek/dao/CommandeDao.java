package fr.afcepf.algeek.dao;

import fr.afcepf.algeek.entity.CommandeEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Repository
public interface CommandeDao extends CrudRepository<CommandeEntity, Long> {
    List<CommandeEntity> findAllByRefClientOrderByDateDeLaCommandeDesc(Long refClient);
}
