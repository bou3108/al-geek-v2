package fr.afcepf.algeek.service;

import fr.afcepf.algeek.dto.InfosBancaires;
import org.springframework.http.ResponseEntity;


public interface BankInfosService {

    ResponseEntity<InfosBancaires> getBankInfosById(Long id);

    ResponseEntity<InfosBancaires> addBankInfos (InfosBancaires infos);

    ResponseEntity<InfosBancaires> updateBankInfos(InfosBancaires infos);

    // ResponseEntity<InfosBancaires> deleteBankInfos(Long id);

}
