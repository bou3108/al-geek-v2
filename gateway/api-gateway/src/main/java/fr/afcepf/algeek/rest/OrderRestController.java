package fr.afcepf.algeek.rest;

import fr.afcepf.algeek.dto.Commande;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/order", headers = "Accept=application/json")
public class OrderRestController {

    ResponseEntityRestCommunicator<Commande> communicator = new ResponseEntityRestCommunicator<Commande>(Commande.class, Commande[].class);

    private String orderManagerUrl = "http://ip:port/manager/order";

    // "http://ip:port/gateway/order/all"
    @GetMapping("/all")
    public ResponseEntity<List<Commande>> getAllOrders() {
        String url = orderManagerUrl + "/all";
        return communicator.getList(url);
    }

    // http://ip:port/gateway/order/id={id}"
    @GetMapping("/id={id}")
    public ResponseEntity<Commande> getOrder(@RequestParam(value = "id", required = false) Long id) {
        String url = orderManagerUrl + "/id=" + id;
        return communicator.get(url);
    }

    // "http://ip:port/gateway/order/add"
    @PostMapping("/add")
    public ResponseEntity<Commande> addOrder(@RequestBody Commande order) {
        String url = orderManagerUrl + "/add";
        return communicator.post(url, order);
    }

    // "http://ip:port/gateway/order/update"
    @PutMapping("/update")
    public ResponseEntity<Commande> updateOrder(@RequestBody Commande order) {
        String url = orderManagerUrl + "/update";
        return communicator.put(url, order);
    }

    // "http://ip:port/gateway/order/id={id}"
    @DeleteMapping("id={id}")
    public ResponseEntity<Commande> deleteOrder(@RequestBody Commande order) {
        String url = orderManagerUrl + "/id=" + order.getId();
        return communicator.delete(url);
    }

    // "http://ip:port/gateway/order/customer/id={id}"
    @GetMapping("/customer/id={id}")
    public ResponseEntity<List<Commande>> getAllOrdersForCustomer(@RequestParam(value = "id", required = true) Long id) {
        String url = orderManagerUrl + "/customer/id=" + id;
        return communicator.getList(url);
    }
}
