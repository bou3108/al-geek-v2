package fr.afcepf.algeek.dao;

import fr.afcepf.algeek.entity.Caracteristique;
import fr.afcepf.algeek.entity.Categorie;
import fr.afcepf.algeek.entity.Produit;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import java.util.List;


public interface CategorieDao extends CrudRepository<Categorie, Long> {
    Categorie findFirstByParentIsNull();
}
