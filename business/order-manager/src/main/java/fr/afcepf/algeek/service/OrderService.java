package fr.afcepf.algeek.service;

import fr.afcepf.algeek.dto.Commande;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface OrderService {

    ResponseEntity<List<Commande>> getOrdersByClient(Long idClient);

    ResponseEntity<List<Commande>> getAllOrders();

    ResponseEntity<Commande> getOrderById(Long id);

    ResponseEntity<Commande> deleteOrderById(Long id);

    ResponseEntity<Commande> addOrder(Commande order);

    ResponseEntity<Commande> updateOrder(Commande order);
}
