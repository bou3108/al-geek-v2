package fr.afcepf.algeek.rest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Slf4j
public class BasicRestCommunicator<T> {

    private RestTemplate restTemplate = new RestTemplate();

    private Class<T> typeObject;
    private Class<T[]> typeArrayObject;

    public BasicRestCommunicator(Class<T> type, Class<T[]> listType) {
        this.typeObject = type;
        this.typeArrayObject = listType;
    }

    public T get(String url) {
        T response = null;
        log.debug("IN get : " + url);
        try {
            response = restTemplate.getForObject(url, typeObject);
            log.debug("OUT get : " + url);
        } catch (Exception ex) {
            log.error("FAULT get : " + ex.getMessage(), ex);
        }
        return response;
    }

    public List<T> getList(String url) {
        List<T> list = null;
        log.debug("IN getList : " + url);
        try {
            T[] array = restTemplate.getForObject(url, typeArrayObject);
            list = Arrays.asList(array);
            log.debug("OUT getList : " + url);
        } catch (Exception ex) {
            log.error("FAULT getList : " + ex.getMessage(), ex);
        }
        return list;
    }

    public T post(String url, T t) {
        T response = null;
        log.debug("IN post : " + url);
        try {
            response = restTemplate.postForObject(url, t, typeObject);
            log.debug("OUT post : " + url);
        } catch (Exception ex) {
            log.error("FAULT post : " + ex.getMessage(), ex);
        }
        return response;
    }

    public List<T> postList(String url, List<T> list) {
        List<T> result = null;
        log.debug("IN postList : " + url);
        try {
            T[] response = restTemplate.postForObject(url, list.toArray(), typeArrayObject);
            result =  Arrays.asList(response);
            log.debug("OUT postList : " + url);
        } catch (Exception ex) {
            log.error("FAULT postList : " + ex.getMessage(), ex);
        }
        return result;
    }

    public T put(String url, T t) {
        log.debug("IN put : " + url);
        try {
            restTemplate.put(url, t);
            log.debug("OUT put : " + url);
            return t;
        } catch (Exception ex) {
            log.error("FAULT put : " + ex.getMessage(), ex);
        }
        return null;
    }

    public boolean delete(String url) {
        log.debug("IN delete : " + url);
        try {
            restTemplate.delete(url);
            log.debug("OUT delete : " + url);
            return true;
        } catch (Exception ex) {
            log.error("FAULT delete : " + ex.getMessage(), ex);
        }
        return false;
    }
}
