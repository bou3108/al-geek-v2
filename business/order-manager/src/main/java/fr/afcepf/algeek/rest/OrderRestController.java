package fr.afcepf.algeek.rest;

import fr.afcepf.algeek.dto.Commande;
import fr.afcepf.algeek.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/order")
public class OrderRestController {

    @Autowired
    private OrderService orderService;


    // A TESTER
    @GetMapping(value = "/customerId={idClient}")
    public ResponseEntity<List<Commande>> getOrdersByClient(@PathVariable Long idClient) {
        return orderService.getOrdersByClient(idClient);
    }

    // A TESTER
    @GetMapping(value = "/all")
    public ResponseEntity<List<Commande>> getAllOrders() {
        return orderService.getAllOrders();
    }

    // A TESTER
    @GetMapping(value = "/id={id}")
    public ResponseEntity<Commande> getOrderById(@PathVariable Long id) {
        return orderService.getOrderById(id);
    }

    // A TESTER
    @DeleteMapping(value = "/id={id}")
    public ResponseEntity<Commande> deleteOrderById(@PathVariable Long id) {
        return orderService.deleteOrderById(id);
    }

    // A TESTER
    @PostMapping(value = "/add")
    public ResponseEntity<Commande> addOrder(@RequestBody Commande order) {
        return orderService.addOrder(order);
    }

    // A TESTER
    @PutMapping(value = "/update")
    public ResponseEntity<Commande> updateOrder(@RequestBody Commande order) {
        return orderService.updateOrder(order);
    }
}
