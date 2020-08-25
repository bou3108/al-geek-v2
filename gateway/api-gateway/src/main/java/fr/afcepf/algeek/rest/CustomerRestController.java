package fr.afcepf.algeek.rest;

import fr.afcepf.algeek.dto.Client;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Slf4j
@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/customer", headers = "Accept=application/json")
public class CustomerRestController {

    private static RestTemplate restTemplate = new RestTemplate();

    private String customerManagerUrl = "http://ip:port/manager/customer";

    // "http://ip:port/gateway/customer/all"
    @GetMapping("/all")
    public List<Client> getAllCustomers() {
        List<Client> listClients = null;
        try {
            Client[] clients = restTemplate.getForObject(customerManagerUrl, Client[].class);
            listClients = Arrays.asList(clients);
        } catch (Exception ex) {
            log.error("getAllCustomers failed : " + ex.getMessage(), ex);
        }
        return listClients;
    }

    // http://ip:port/gateway/customer/id={id}"
    @GetMapping("/id={id}")
    public Client getCustomer(@RequestParam(value = "id", required = false) Long id) {
        try {
            return restTemplate.getForObject(customerManagerUrl + "/" + id, Client.class);
        } catch (Exception ex) {
            log.error("getCustomer failed : " + ex.getMessage(), ex);
        }
        return null;
    }

    // "http://ip:port/gateway/customer/add"
    @PostMapping("/add")
    public Client addCustomer(@RequestBody Client customer) {
        try {
            return restTemplate.postForObject(customerManagerUrl, customer ,Client.class);
        } catch (Exception ex) {
            log.error("addCustomer failed : " + ex.getMessage(), ex);
        }
        return null;
    }

    // "http://ip:port/gateway/customer/update"
    @PutMapping("/update")
    public void updateCustomer(@RequestBody Client customer) {
        try {
            restTemplate.put(customerManagerUrl, customer);
        } catch (Exception ex) {
            log.error("updateCustomer failed : " + ex.getMessage(), ex);
        }
    }

    // "http://ip:port/gateway/customer/id={id}"
    @DeleteMapping("/id={id}")
    public Client deleteCustomer(@RequestBody Client customer) {
        try {
            restTemplate.delete(customerManagerUrl + "/id=" + customer.getId());
        } catch (Exception ex) {
            log.error("deleteCustomer failed : " + ex.getMessage(), ex);
        }
        return null;
    }

    // "http://ip:port/gateway/customer/authentication?email=dev@dev.dev?password=xxxxxxxx"
    @GetMapping("/authentication")
    public Client connect(@RequestParam(value = "email", required = false) String email, @RequestParam(value = "password", required = false) String password) {
        Client client = null;
        try {
            client = restTemplate.getForObject(customerManagerUrl + "/authentication/email=" + email + "&password=" + password, Client.class);
        } catch (Exception ex) {
            log.error("connect failed : " + ex.getMessage(), ex);
        }
        return client;
    }
}
