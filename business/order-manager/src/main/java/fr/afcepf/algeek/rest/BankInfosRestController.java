package fr.afcepf.algeek.rest;

import fr.afcepf.algeek.dto.InfosBancaires;
import fr.afcepf.algeek.service.BankInfosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/bankInfos")
public class BankInfosRestController {

    @Autowired
    private BankInfosService bankInfosService;

    @GetMapping(value = "/id={id}")
    public ResponseEntity<InfosBancaires> getBankInfosById(@PathVariable Long id) {
        return bankInfosService.getBankInfosById(id);
    }

    // OK
    @PostMapping(value = "/add")
    public ResponseEntity<InfosBancaires> addBankInfos (@RequestBody InfosBancaires infos) {
        return bankInfosService.addBankInfos(infos);
    }

    // OK
    @PutMapping(value = "/update")
    public ResponseEntity<InfosBancaires> updateBankInfos (@RequestBody InfosBancaires infos) {
        return bankInfosService.updateBankInfos(infos);
    }

    //
    // @DeleteMapping(value = "/id={id}")
    // public ResponseEntity<InfosBancaires> deleteBankInfos (@PathVariable Long id) {
    //     return bankInfosService.deleteBankInfos(id);
    // }
}
