package fr.afcepf.algeek.dao;

import fr.afcepf.algeek.entity.InfosBancairesEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

@Service
public interface InfosBancairesDao extends CrudRepository<InfosBancairesEntity, Long> {
}
