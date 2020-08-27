package fr.afcepf.algeek.dao;

import fr.afcepf.algeek.entity.InfosBancairesEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Repository
public interface InfosBancairesDao extends CrudRepository<InfosBancairesEntity, Long> {
}
