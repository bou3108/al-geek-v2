package fr.afcepf.algeek.service;

import fr.afcepf.algeek.dto.Commande;
import fr.afcepf.algeek.dto.InfosBancaires;
import fr.afcepf.algeek.dto.LigneCommande;
import fr.afcepf.algeek.rest.ResponseEntityRestCommunicator;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderLineServiceImpl implements OrderLineService {

    private ResponseEntityRestCommunicator<LigneCommande> lineCommunicator =
            new ResponseEntityRestCommunicator<>(LigneCommande.class, LigneCommande[].class);

    private String urlProductAPI = "http://localhost:8080/db/order";

    // TO DO
    @Override
    public ResponseEntity<LigneCommande> getOrderLineById(Long id) {
        return null;
    }

    // TO DO
    @Override
    public ResponseEntity<LigneCommande> deletOrderLineById(Long id) {
        return null;
    }
    // TO DO
    @Override
    public ResponseEntity<LigneCommande> addOrderLine(LigneCommande line) {
        return null;
    }

    // TO DO
    @Override
    public ResponseEntity<LigneCommande> updateOrderLine(LigneCommande line) {
        return null;
    }

    // TO DO
    @Override
    public ResponseEntity<List<LigneCommande>> getAllOrderLinesByOrder(Commande order) {
        return null;
    }

    // TO DO
    @Override
    public ResponseEntity<List<LigneCommande>> getAllOrderLines() {
        return null;
    }
}
