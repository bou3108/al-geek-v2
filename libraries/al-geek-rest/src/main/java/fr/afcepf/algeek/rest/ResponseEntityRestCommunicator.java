package fr.afcepf.algeek.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

public class ResponseEntityRestCommunicator<T> {

    private RestTemplate restTemplate = new RestTemplate();

    private Class<T> typeObject;
    private Class<T[]> typeArrayObject;

    private HttpStatus defaultHttpStatus = HttpStatus.BAD_REQUEST;

    public ResponseEntityRestCommunicator(Class<T> type, Class<T[]> listType) {
        this.typeObject = type;
        this.typeArrayObject = listType;
    }


    public ResponseEntity<T> get(String url) {
        return get(url, null);
    }

    public ResponseEntity<T> get(String url, HttpStatus customStatus) {
        if (customStatus == null) {
            customStatus = defaultHttpStatus;
        }

        try {
            T response = restTemplate.getForObject(url, typeObject);
            return new ResponseEntity<T>(response, HttpStatus.OK);
        } catch (Exception ex) {

        }
        return new ResponseEntity<T>(customStatus);
    }


    public ResponseEntity<List<T>> getList(String url) {
        return getList(url, null);
    }

    public ResponseEntity<List<T>> getList(String url, HttpStatus customStatus) {
        if (customStatus == null) {
            customStatus = defaultHttpStatus;
        }

        List<T> list = null;
        try {
            T[] array = restTemplate.getForObject(url, typeArrayObject);
            list = Arrays.asList(array);
            return new ResponseEntity<List<T>>(list, HttpStatus.OK);
        } catch (Exception ex) {

        }
        return new ResponseEntity<List<T>>(list, customStatus);
    }


    public ResponseEntity<T> post(String url, T t) {
        return post(url, t,null);
    }

    public ResponseEntity<T> post(String url, T t, HttpStatus customStatus) {
        if (customStatus == null) {
            customStatus = defaultHttpStatus;
        }

        try {
            T response = restTemplate.postForObject(url, t,typeObject);
            return new ResponseEntity<T>(response, HttpStatus.OK);
        } catch (Exception ex) {

        }
        return new ResponseEntity<T>(customStatus);
    }


    public ResponseEntity<T> put(String url, T t) {
        return put(url, t,null);
    }

    public ResponseEntity<T> put(String url, T t, HttpStatus customStatus) {
        if (customStatus == null) {
            customStatus = defaultHttpStatus;
        }

        try {
            restTemplate.put(url, t);
            return new ResponseEntity<T>(t, HttpStatus.OK);
        } catch (Exception ex) {

        }
        return new ResponseEntity<T>(customStatus);
    }


    public ResponseEntity<T> delete(String url) {
        return delete(url, null);
    }

    public ResponseEntity<T> delete(String url, HttpStatus customStatus) {
        if (customStatus == null) {
            customStatus = defaultHttpStatus;
        }

        try {
            restTemplate.delete(url);
            return new ResponseEntity<T>(HttpStatus.OK);
        } catch (Exception ex) {

        }
        return new ResponseEntity<T>(customStatus);
    }
}
