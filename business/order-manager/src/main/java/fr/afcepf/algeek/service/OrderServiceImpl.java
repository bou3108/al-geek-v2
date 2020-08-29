package fr.afcepf.algeek.service;

import fr.afcepf.algeek.dto.Commande;
import fr.afcepf.algeek.rest.ResponseEntityRestCommunicator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    private ResponseEntityRestCommunicator<Commande> orderCommunicator =
            new ResponseEntityRestCommunicator<>(Commande.class, Commande[].class);

    @Value("${algeek.db.order.address}")
    private String urlOrderAPI;
    // private String urlProductAPI = "http://localhost:8080/db/order/order";

    // OK
    @Override
    public ResponseEntity<List<Commande>> getOrdersByClient(Long idClient) {
        String url = urlOrderAPI + "/order/customer/id=" + idClient;
        return orderCommunicator.getList(url);
    }

    // OK
    @Override
    public ResponseEntity<List<Commande>> getAllOrders() {
        String url = urlOrderAPI + "/order/all";
        return orderCommunicator.getList(url);
    }

    // OK
    @Override
    public ResponseEntity<Commande> getOrderById(Long id) {
        String url  = urlOrderAPI + "/order/id=" + id;
        return orderCommunicator.get(url);
    }

    // OK
    @Override
    public ResponseEntity<Commande> deleteOrderById(Long id) {
        String url = urlOrderAPI + "/order/id=" + id;
        return orderCommunicator.delete(url);
    }

    // OK
    @Override
    public ResponseEntity<Commande> addOrder(Commande order) {
        String url = urlOrderAPI + "/order/add";
        return orderCommunicator.post(url, order);
    }

    // OK
    @Override
    public ResponseEntity<Commande> updateOrder(Commande order) {
        String url = urlOrderAPI + "/order/update";
        return orderCommunicator.put(url, order);
    }
}
