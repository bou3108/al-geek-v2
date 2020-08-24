package fr.afcepf.algeek.dao;

import fr.afcepf.algeek.entity.Caracteristique;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;


public interface CategorieDao extends CrudRepository<Caracteristique, Long> {
}
