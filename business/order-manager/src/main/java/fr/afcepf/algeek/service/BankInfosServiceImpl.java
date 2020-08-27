package fr.afcepf.algeek.service;

import fr.afcepf.algeek.dto.InfosBancaires;
import fr.afcepf.algeek.rest.ResponseEntityRestCommunicator;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class BankInfosServiceImpl implements BankInfosService {

    private ResponseEntityRestCommunicator<InfosBancaires> bankInfosCommunicator =
            new ResponseEntityRestCommunicator<>(InfosBancaires.class, InfosBancaires[].class);

    private String urlProductAPI = "http://localhost:8080/db/order/bank";



    // A TESTER
    @Override
    public ResponseEntity<InfosBancaires> getBankInfosById(Long id) {
        String url = urlProductAPI + "/id=" + id;
        return bankInfosCommunicator.get(url);
    }

    // A TESTER
    @Override
    public ResponseEntity<InfosBancaires> addBankInfos(InfosBancaires infos) {
        String url = urlProductAPI + "/add";
        return bankInfosCommunicator.post(url, infos);
    }

    // A TESTER
    @Override
    public ResponseEntity<InfosBancaires> updateBankInfos(InfosBancaires infos) {
        String url = urlProductAPI + "/update";
        return bankInfosCommunicator.put(url, infos);
    }

    // A TESTER
    @Override
    public ResponseEntity<InfosBancaires> deleteBankInfos(Long id) {
        String url = urlProductAPI + "/id=" + id;
        return bankInfosCommunicator.delete(url);
    }
}
