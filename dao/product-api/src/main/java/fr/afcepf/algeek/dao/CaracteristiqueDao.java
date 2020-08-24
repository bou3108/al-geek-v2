package fr.afcepf.algeek.dao;

import fr.afcepf.algeek.entity.Caracteristique;
import fr.afcepf.algeek.entity.Produit;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import java.util.List;


public interface CaracteristiqueDao extends CrudRepository<Caracteristique, Long> {
    List<Caracteristique> findByProduit(Produit produit);
}
