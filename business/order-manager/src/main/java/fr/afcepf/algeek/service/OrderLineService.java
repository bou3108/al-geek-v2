package fr.afcepf.algeek.service;

import fr.afcepf.algeek.dto.Commande;
import fr.afcepf.algeek.dto.LigneCommande;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface OrderLineService {

    ResponseEntity<LigneCommande> getOrderLineById(Long id);

    ResponseEntity<LigneCommande> deletOrderLineById(Long id);

    ResponseEntity<LigneCommande> addOrderLine (LigneCommande line);

    ResponseEntity<LigneCommande> updateOrderLine (LigneCommande line);

    ResponseEntity<List<LigneCommande>> getAllOrderLinesByOrder(Commande order);

    ResponseEntity<List<LigneCommande>> getAllOrderLines();
}
