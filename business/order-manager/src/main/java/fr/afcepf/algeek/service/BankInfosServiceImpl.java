package fr.afcepf.algeek.service;

import fr.afcepf.algeek.dto.InfosBancaires;
import fr.afcepf.algeek.rest.ResponseEntityRestCommunicator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class BankInfosServiceImpl implements BankInfosService {

    private ResponseEntityRestCommunicator<InfosBancaires> bankInfosCommunicator =
            new ResponseEntityRestCommunicator<>(InfosBancaires.class, InfosBancaires[].class);

    @Value("${algeek.db.order.address}")
    private String urlProductAPI;
    // private String urlProductAPI = "http://localhost:8080/db/order/bankInfos";



    // OK
    @Override
    public ResponseEntity<InfosBancaires> getBankInfosById(Long id) {
        String url = urlProductAPI + "/bankInfos/id=" + id;
        return bankInfosCommunicator.get(url);
    }

    // OK
    @Override
    public ResponseEntity<InfosBancaires> addBankInfos(InfosBancaires infos) {
        String url = urlProductAPI + "/bankInfos/add";
        return bankInfosCommunicator.post(url, infos);
    }

    // OK
    @Override
    public ResponseEntity<InfosBancaires> updateBankInfos(InfosBancaires infos) {
        String url = urlProductAPI + "/bankInfos/update";
        return bankInfosCommunicator.put(url, infos);
    }

    // A TESTER
    // @Override
    // public ResponseEntity<InfosBancaires> deleteBankInfos(Long id) {
    //     String url = urlProductAPI + "/id=" + id;
    //     return bankInfosCommunicator.delete(url);
    // }
}
