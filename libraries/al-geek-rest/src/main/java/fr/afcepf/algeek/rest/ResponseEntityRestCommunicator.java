package fr.afcepf.algeek.rest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Slf4j
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
        log.debug("IN get : " + url);
        if (customStatus == null) {
            customStatus = defaultHttpStatus;
        }

        try {
            T response = restTemplate.getForObject(url, typeObject);
            log.debug("OUT get : " + url);
            return new ResponseEntity<T>(response, HttpStatus.OK);
        } catch (Exception ex) {
            log.error("FAULT get : " + ex.getMessage(), ex);
        }
        return new ResponseEntity<T>(customStatus);
    }


    public ResponseEntity<List<T>> getList(String url) {
        return getList(url, null);
    }

    public ResponseEntity<List<T>> getList(String url, HttpStatus customStatus) {
        log.debug("IN getList : " + url);
        if (customStatus == null) {
            customStatus = defaultHttpStatus;
        }

        List<T> list = null;
        try {
            T[] array = restTemplate.getForObject(url, typeArrayObject);
            list = Arrays.asList(array);
            log.debug("OUT getList : " + url);
            return new ResponseEntity<List<T>>(list, HttpStatus.OK);
        } catch (Exception ex) {
            log.error("FAULT getList : " + ex.getMessage(), ex);
        }
        return new ResponseEntity<List<T>>(list, customStatus);
    }


    public ResponseEntity<T> post(String url, T t) {
        return post(url, t,null);
    }

    public ResponseEntity<T> post(String url, T t, HttpStatus customStatus) {
        log.debug("IN post : " + url);
        if (customStatus == null) {
            customStatus = defaultHttpStatus;
        }

        try {
            T response = restTemplate.postForObject(url, t,typeObject);
            log.debug("OUT post : " + url);
            return new ResponseEntity<T>(response, HttpStatus.OK);
        } catch (Exception ex) {
            log.error("FAULT post : " + ex.getMessage(), ex);
        }
        return new ResponseEntity<T>(customStatus);
    }


    public ResponseEntity<List<T>> postList(String url, List<T> list) {
        return postList(url, list,null);
    }

    public ResponseEntity<List<T>> postList(String url, List<T> list, HttpStatus customStatus) {
        log.debug("IN postList : " + url);
        if (customStatus == null) {
            customStatus = defaultHttpStatus;
        }

        try {
            T[] response = restTemplate.postForObject(url, list.toArray(), typeArrayObject);
            log.debug("OUT postList : " + url);
            return new ResponseEntity<List<T>>(Arrays.asList(response), HttpStatus.OK);
        } catch (Exception ex) {
            log.error("FAULT postList : " + ex.getMessage(), ex);
        }
        return new ResponseEntity<List<T>>(customStatus);
    }


    public ResponseEntity<List<T>> postForList(String url, List<Long> list) {
        return postForList(url, list,null);
    }

    public ResponseEntity<List<T>> postForList(String url, List<Long> list, HttpStatus customStatus) {
        log.debug("IN postForList : " + url);
        if (customStatus == null) {
            customStatus = defaultHttpStatus;
        }

        try {
            T[] response = restTemplate.postForObject(url, list.toArray(), typeArrayObject);
            log.debug("OUT postForList : " + url);
            return new ResponseEntity<List<T>>(Arrays.asList(response), HttpStatus.OK);
        } catch (Exception ex) {
            log.error("FAULT postForList : " + ex.getMessage(), ex);
        }
        return new ResponseEntity<List<T>>(customStatus);
    }


    public ResponseEntity<T> put(String url, T t) {
        return put(url, t,null);
    }

    public ResponseEntity<T> put(String url, T t, HttpStatus customStatus) {
        log.debug("IN put : " + url);
        if (customStatus == null) {
            customStatus = defaultHttpStatus;
        }

        try {
            restTemplate.put(url, t);
            log.debug("OUT put : " + url);
            return new ResponseEntity<T>(t, HttpStatus.OK);
        } catch (Exception ex) {
            log.error("FAULT put : " + ex.getMessage(), ex);
        }
        return new ResponseEntity<T>(customStatus);
    }


    public ResponseEntity<T> delete(String url) {
        return delete(url, null);
    }

    public ResponseEntity<T> delete(String url, HttpStatus customStatus) {
        log.debug("IN delete : " + url);
        if (customStatus == null) {
            customStatus = defaultHttpStatus;
        }

        try {
            restTemplate.delete(url);
            log.debug("OUT delete : " + url);
            return new ResponseEntity<T>(HttpStatus.OK);
        } catch (Exception ex) {
            log.error("FAULT delete : " + ex.getMessage(), ex);
        }
        return new ResponseEntity<T>(customStatus);
    }
}
