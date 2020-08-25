package fr.afcepf.algeek.rest;

import fr.afcepf.algeek.dto.Commande;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Slf4j
@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/order", headers = "Accept=application/json")
public class OrderRestController {

    private static RestTemplate restTemplate = new RestTemplate();

    private String orderManagerUrl = "http://ip:port/manager/order";

    // "http://ip:port/gateway/order/all"
    @GetMapping("/all")
    public List<Commande> getAllOrders() {
        List<Commande> ordersList = null;
        try {
            Commande[] orders = restTemplate.getForObject(orderManagerUrl, Commande[].class);
            ordersList = Arrays.asList(orders);
        } catch (Exception ex) {
            log.error("getAllOrders : " + ex.getMessage(), ex);
        }
        return ordersList;
    }

    // http://ip:port/gateway/order/id={id}"
    @GetMapping("/id={id}")
    public Commande getOrder(@RequestParam(value = "id", required = false) Long id) {
        Commande order = null;
        try {
            order = restTemplate.getForObject(orderManagerUrl + "/" + id, Commande.class);
        } catch (Exception ex) {
            log.error("getOrder : " + ex.getMessage(), ex);
        }
        return order;
    }
    // List<Commande> getCommandesPourClient(Long clientId)

    // "http://ip:port/gateway/order/add"
    @PostMapping("/add")
    public Commande addOrder(@RequestBody Commande order) {
        try {
            Commande addedOrder = restTemplate.postForObject(orderManagerUrl, order, Commande.class);
            return addedOrder;
        } catch (Exception ex) {
            log.error("addOrder : " + ex.getMessage(), ex);
        }
        return null;
    }

    // "http://ip:port/al-geek-gateway/order/update"
    @PutMapping("/update")
    public void updateOrder(@RequestBody Commande order) {
        try {
            restTemplate.put(orderManagerUrl, order);
        } catch (Exception ex) {
            log.error("updateOrder : " + ex.getMessage(), ex);
        }
    }

    // "http://ip:port/al-geek-gateway/order"
    @DeleteMapping("id={id}")
    public Commande deleteOrder(@RequestBody Commande order) {
        try {
            restTemplate.delete(orderManagerUrl + "/id=" + order.getId());
        } catch (Exception ex) {
            log.error("deleteOrder : " + ex.getMessage(), ex);
        }
        return null;
    }

    // "http://ip:port/al-geek-gateway/order/customer/id={id}"
    @GetMapping("/customer/id={id}")
    public List<Commande> getAllOrdersForCustomer(@RequestParam(value = "id", required = true) Long id) {
        List<Commande> ordersList = null;
        try {
            if (id != null) {
                Commande[] orders = restTemplate.getForObject(orderManagerUrl + "/customer/id=" + id, Commande[].class);
                ordersList = Arrays.asList(orders);
            }
        } catch (Exception ex) {
            log.error("getAllOrdersForCustomer : " + ex.getMessage(), ex);
        }
        return ordersList;
    }
}
