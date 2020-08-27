package fr.afcepf.algeek.rest;

import fr.afcepf.algeek.dto.LigneCommande;
import fr.afcepf.algeek.service.OrderLineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/orderLine")
public class OrderLineRestController {

    @Autowired
    private OrderLineService orderLineService;


    // A TESTER
    @GetMapping(value = "/id={id}")
    public ResponseEntity<LigneCommande> getOrderLineById(@PathVariable Long id) {
        return orderLineService.getOrderLineById(id);
    }

    // A TESTER
    @DeleteMapping(value = "/id={id}")
    public ResponseEntity<LigneCommande> deleteOrderLineById(@PathVariable Long id) {
        return orderLineService.deleteOrderLineById(id);
    }

    // A TESTER
    @PostMapping(value = "/add")
    public ResponseEntity<LigneCommande> addOrderLine (@RequestBody LigneCommande line) {
        return orderLineService.addOrderLine(line);
    }

    // A TESTER
    @PutMapping(value = "/update")
    public ResponseEntity<LigneCommande> updateOrderLine (@RequestBody LigneCommande line) {
        return orderLineService.updateOrderLine(line);
    }

    // A TESTER
    @GetMapping (value = "/order/id={id}")
    public ResponseEntity<List<LigneCommande>> getAllOrderLinesByOrderId(@PathVariable Long id) {
        return orderLineService.getAllOrderLinesByOrderId(id);
    }

    // A TESTER
    @GetMapping (value = "/all")
    public ResponseEntity<List<LigneCommande>> getAllOrderLines() {
        return orderLineService.getAllOrderLines();
    }
}
