package fr.afcepf.algeek.service;

import fr.afcepf.algeek.dto.Commande;
import fr.afcepf.algeek.rest.ResponseEntityRestCommunicator;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    private ResponseEntityRestCommunicator<Commande> orderCommunicator =
            new ResponseEntityRestCommunicator<>(Commande.class, Commande[].class);

    private String urlProductAPI = "http://localhost:8080/db/order";


    // A TESTER
    @Override
    public ResponseEntity<List<Commande>> getOrdersByClient(Long idClient) {
        String url = urlProductAPI + "/all/id=" + idClient;
        return orderCommunicator.getList(url);
    }

    // A TESTER
    @Override
    public ResponseEntity<List<Commande>> getAllOrders() {
        String url = urlProductAPI + "/all";
        return orderCommunicator.getList(url);
    }

    // A TESTER
    @Override
    public ResponseEntity<Commande> getOrderById(Long id) {
        String url  = urlProductAPI + "/id=" + id;
        return orderCommunicator.get(url);
    }

    // A TESTER
    @Override
    public ResponseEntity<Commande> deleteOrderById(Long id) {
        String url = urlProductAPI + "/id=" + id;
        return orderCommunicator.get(url);
    }

    // A TESTER
    @Override
    public ResponseEntity<Commande> addOrder(Commande order) {
        String url = urlProductAPI + "/add";
        return orderCommunicator.post(url, order);
    }

    // A TESTER
    @Override
    public ResponseEntity<Commande> updateOrder(Commande order) {
        String url = urlProductAPI + "/update";
        return orderCommunicator.put(url, order);
    }
}
