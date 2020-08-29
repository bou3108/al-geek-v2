package fr.afcepf.algeek.service;

import fr.afcepf.algeek.dto.LigneCommande;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface OrderLineService {

    ResponseEntity<LigneCommande> getOrderLineById(Long id);

    ResponseEntity<LigneCommande> deleteOrderLineById(Long id);

    ResponseEntity<LigneCommande> addOrderLine (LigneCommande line);

    ResponseEntity<LigneCommande> updateOrderLine (LigneCommande line);

    ResponseEntity<List<LigneCommande>> getAllOrderLinesByOrderId(Long id);

    ResponseEntity<List<LigneCommande>> getAllOrderLines();
}
