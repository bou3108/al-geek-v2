package fr.afcepf.algeek.rest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/sales")
public class StockController {

    ResponseEntityRestCommunicator<Object> communicator = new ResponseEntityRestCommunicator<Object>(Object.class, Object[].class);

    private String stockManagerUrl = "http://ip:port/manager/stock/";

    // "http://ip:port/al-geek-gateway/stock/"
    @GetMapping("")
    public ResponseEntity<List<Object>> getAllStockEntries() {
        String url = stockManagerUrl + "/all";
        return communicator.getList(stockManagerUrl + "all");
    }
}
