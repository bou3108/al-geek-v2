package fr.afcepf.algeek.rest;

import fr.afcepf.algeek.dto.Commande;
import fr.afcepf.algeek.dto.InfosBancaires;
import fr.afcepf.algeek.dto.LigneCommande;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/order")
public class OrderRestController {

    private final ResponseEntityRestCommunicator<Commande> communicator = new ResponseEntityRestCommunicator<Commande>(Commande.class, Commande[].class);
    private final ResponseEntityRestCommunicator<LigneCommande> lgCommunicator = new ResponseEntityRestCommunicator<LigneCommande>(LigneCommande.class, LigneCommande[].class);
    private final ResponseEntityRestCommunicator<InfosBancaires> biCommunicator = new ResponseEntityRestCommunicator<InfosBancaires>(InfosBancaires.class, InfosBancaires[].class);

    @Value("${algeek.manager.order.address}")
    private String orderManagerUrl;


    // "http://ip:port/gateway/order/all"
    @GetMapping("/all") // OK
    public ResponseEntity<List<Commande>> getAllOrders() {
        String url = orderManagerUrl + "/all";
        return communicator.getList(url);
    }

    // http://ip:port/gateway/order/id={id}"
    @GetMapping("/id={id}") // OK
    public ResponseEntity<Commande> getOrder(@PathVariable Long id) {
        String url = orderManagerUrl + "/id=" + id;
        return communicator.get(url);
    }

    // "http://ip:port/gateway/order/add"
    @PostMapping("/add") // OK
    public ResponseEntity<Commande> addOrder(@RequestBody Commande order) {
        String url = orderManagerUrl + "/add";
        return communicator.post(url, order);
    }

    // "http://ip:port/gateway/order/update"
    @PutMapping("/update") // OK
    public ResponseEntity<Commande> updateOrder(@RequestBody Commande order) {
        String url = orderManagerUrl + "/update";
        return communicator.put(url, order);
    }

    // "http://ip:port/gateway/order/id={id}"
    @DeleteMapping("id={id}")
    public ResponseEntity<Commande> deleteOrder(@PathVariable Long id) {
        String url = orderManagerUrl + "/id=" + id;
        return communicator.delete(url);
    }

    // "http://ip:port/gateway/order/customer/id={id}"
    @GetMapping("/customer/id={id}")
    public ResponseEntity<List<Commande>> getAllOrdersForCustomer(@PathVariable Long id) {
        String url = orderManagerUrl + "/customer/id=" + id;
        return communicator.getList(url);
    }

    // "http://ip:port/gateway/order/add"
    @PostMapping("/lines/add")
    public ResponseEntity<LigneCommande> addLineOrder(@RequestBody LigneCommande line) {
        String url = orderManagerUrl + "/lines/add";
        return lgCommunicator.post(url, line);
    }

    // "http://ip:port/gateway/order/add"
    @PostMapping("/bank/add")
    public ResponseEntity<InfosBancaires> addBankInformations(@RequestBody InfosBancaires infos) {
        String url = orderManagerUrl + "/bank/add";
        return biCommunicator.post(url, infos);
    }

}
