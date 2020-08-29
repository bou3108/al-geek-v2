package fr.afcepf.algeek.rest;

import fr.afcepf.algeek.dto.Client;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/customer")
public class CustomerRestController {

    private final ResponseEntityRestCommunicator<Client> communicator = new ResponseEntityRestCommunicator<Client>(Client.class, Client[].class);

    @Value("${algeek.manager.customer.address}")
    private String customerManagerUrl;


    // "http://ip:port/gateway/customer/all"
    @GetMapping("/all")
    public ResponseEntity<List<Client>> getAllCustomers() {
        String url = customerManagerUrl + "/all";
        return communicator.getList(url);
    }

    // http://ip:port/gateway/customer/id={id}"
    @GetMapping("/id={id}")
    public ResponseEntity<Client> getCustomer(@PathVariable Long id) {
        String url = customerManagerUrl + "/id=" + id;
        return communicator.get(url);
    }

    // "http://ip:port/gateway/customer/add"
    @PostMapping("/add")
    public ResponseEntity<Client> addCustomer(@RequestBody Client customer) {
        String url = customerManagerUrl + "/add";
        return communicator.post(url, customer);
    }

    // "http://ip:port/gateway/customer/update"
    @PutMapping("/update")
    public ResponseEntity<Client> updateCustomer(@RequestBody Client customer) {
        String url = customerManagerUrl + "/update";
        return communicator.put(url, customer);
    }

    // "http://ip:port/gateway/customer/id={id}"
    @DeleteMapping("/id={id}")
    public ResponseEntity<Client> deleteCustomer(@PathVariable Long id) {
        String url = customerManagerUrl + "/id=" + id;
        return communicator.delete(url);
    }

    // "http://ip:port/gateway/customer/authentication/email=dev@dev.dev?password=xxxxxxxx"
    @GetMapping("/authentication/email={email}&password={password}")
    public ResponseEntity<Client> connect(@PathVariable String email, @PathVariable String password) {
        String url = customerManagerUrl + "/authentication/email=" + email + "&password=" + password;
        return communicator.get(url);
    }

    // "http://ip:port/gateway/customer/register/password=xxxxxxxx"
    @PostMapping("/register/password={password}")
    public ResponseEntity<Client> doRegister(@RequestBody Client customer, @PathVariable String password) {
        String url = customerManagerUrl + "/register/password=" + password;
        return communicator.post(url, customer);
    }
}
