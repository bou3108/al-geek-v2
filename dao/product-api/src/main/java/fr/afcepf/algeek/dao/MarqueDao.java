package fr.afcepf.algeek.dao;

import fr.afcepf.algeek.entity.Marque;
import org.springframework.data.repository.CrudRepository;

public interface MarqueDao extends CrudRepository<Marque, Long> {
}
