package fr.afcepf.algeek.dao;

import fr.afcepf.algeek.entity.InfosBancairesEntity;
import org.springframework.data.repository.CrudRepository;

public interface InfosBancairesDao extends CrudRepository<InfosBancairesEntity, Long> {
}
