package fr.afcepf.algeek.service;

import fr.afcepf.algeek.dto.Meteo;
import fr.afcepf.algeek.rest.ResponseEntityRestCommunicator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class MeteoService {

    @Value("${algeek.gateway.address}")
    private String gatewayUrl;

    private final ResponseEntityRestCommunicator<Meteo> communicator = new ResponseEntityRestCommunicator<>(Meteo.class, Meteo[].class);

    public Meteo getMeteoByZipCode (String zipCode) {
        String url = gatewayUrl + "/meteo/zipcode/" + zipCode;
        ResponseEntity<List<Meteo>> response = communicator.getList(url);
        try {
            if(response.getStatusCode() == HttpStatus.OK) {
                Meteo meteo = response.getBody().get(0);
                return meteo;
            } else {
                return null;
            }
        } catch (Exception ex) {
            return null;
        }
    }
}
