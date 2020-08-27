package fr.afcepf.algeek.service;

import fr.afcepf.algeek.dto.Produit;
import fr.afcepf.algeek.dto.TypeProduit;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ConfiguratorService {

    ResponseEntity<List<TypeProduit>> getTypesComposants();

    boolean estCompatibleAvec(Produit premier, Produit second);
}
