package fr.afcepf.algeek.dao;

import fr.afcepf.algeek.entity.TypeProduit;
import org.springframework.data.repository.CrudRepository;

public interface TypeProduitDao extends CrudRepository<TypeProduit, Long> {

    TypeProduit findByNom(String nom);
}
