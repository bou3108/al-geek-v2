package fr.afcepf.algeek.dao;

import fr.afcepf.algeek.entity.Produit;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProduitDao extends CrudRepository<Produit, Long> {

    List<Produit> findByTypeId(Long id);
}
