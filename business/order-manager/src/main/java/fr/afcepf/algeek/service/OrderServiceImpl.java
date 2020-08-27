package fr.afcepf.algeek.service;

import fr.afcepf.algeek.dto.Commande;
import fr.afcepf.algeek.dto.InfosBancaires;
import fr.afcepf.algeek.dto.LigneCommande;
import fr.afcepf.algeek.rest.ResponseEntityRestCommunicator;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    private ResponseEntityRestCommunicator<Commande> orderCommunicator =
            new ResponseEntityRestCommunicator<>(Commande.class, Commande[].class);

    private String urlProductAPI = "http://localhost:8080/db/order";


    // TO DO
    @Override
    public ResponseEntity<List<Commande>> getOrdersByClient(Long idClient) {
        return null;
    }

    // TO DO
    @Override
    public ResponseEntity<List<Commande>> getAllOrders() {
        return null;
    }

    // TO DO
    @Override
    public ResponseEntity<Commande> getOrderById(Long id) {
        return null;
    }

    // TO DO
    @Override
    public ResponseEntity<Commande> deleteOrderById(Long id) {
        return null;
    }

    // TO DO
    @Override
    public ResponseEntity<Commande> addOrder(Commande order) {
        return null;
    }

    // TO DO
    @Override
    public ResponseEntity<Commande> updateOrder(Commande order) {
        return null;
    }
}
