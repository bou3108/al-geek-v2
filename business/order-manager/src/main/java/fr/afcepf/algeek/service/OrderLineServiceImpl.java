package fr.afcepf.algeek.service;

import fr.afcepf.algeek.dto.LigneCommande;
import fr.afcepf.algeek.rest.ResponseEntityRestCommunicator;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderLineServiceImpl implements OrderLineService {

    private ResponseEntityRestCommunicator<LigneCommande> lineCommunicator =
            new ResponseEntityRestCommunicator<>(LigneCommande.class, LigneCommande[].class);

    private String urlProductAPI = "http://localhost:8080/db/order/orderLine";

    // OK
    @Override
    public ResponseEntity<LigneCommande> getOrderLineById(Long id) {
        String url = urlProductAPI + "/id=" + id;
        return lineCommunicator.get(url);
    }

    // OK
    @Override
    public ResponseEntity<LigneCommande> deleteOrderLineById(Long id) {
        String url = urlProductAPI + "/id=" + id;
        return lineCommunicator.delete(url);
    }
    // OK
    @Override
    public ResponseEntity<LigneCommande> addOrderLine(LigneCommande line) {
        String url = urlProductAPI + "/add";
        return lineCommunicator.post(url, line);
    }

    // OK
    @Override
    public ResponseEntity<LigneCommande> updateOrderLine(LigneCommande line) {
        String url = urlProductAPI + "/update";
        return lineCommunicator.put(url, line);
    }

    // OK
    @Override
    public ResponseEntity<List<LigneCommande>> getAllOrderLinesByOrderId(Long id) {
        String url = urlProductAPI + "/order/id=" + id;
        return lineCommunicator.getList(url);
    }

    // A TESTER
    @Override
    public ResponseEntity<List<LigneCommande>> getAllOrderLines() {
        String url = urlProductAPI + "/all";
        return lineCommunicator.getList(url);
    }
}
