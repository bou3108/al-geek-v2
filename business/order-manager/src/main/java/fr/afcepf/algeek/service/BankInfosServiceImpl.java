package fr.afcepf.algeek.service;

import fr.afcepf.algeek.dto.Commande;
import fr.afcepf.algeek.dto.InfosBancaires;
import fr.afcepf.algeek.dto.LigneCommande;
import fr.afcepf.algeek.rest.ResponseEntityRestCommunicator;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class BankInfosServiceImpl implements BankInfosService {

    private ResponseEntityRestCommunicator<InfosBancaires> bankInfosCommunicator =
            new ResponseEntityRestCommunicator<>(InfosBancaires.class, InfosBancaires[].class);

    private String urlProductAPI = "http://localhost:8080/db/order";


    // TO DO
    @Override
    public ResponseEntity<InfosBancaires> getBankInfosById(Long id) {
        return null;
    }

    // TO DO
    @Override
    public ResponseEntity<InfosBancaires> addBankInfos(InfosBancaires infos) {
        return null;
    }

    // TO DO
    @Override
    public ResponseEntity<InfosBancaires> updateBankInfos(InfosBancaires infos) {
        return null;
    }

    // TO DO
    @Override
    public ResponseEntity<InfosBancaires> deleteBankInfos(Long id) {
        return null;
    }
}
