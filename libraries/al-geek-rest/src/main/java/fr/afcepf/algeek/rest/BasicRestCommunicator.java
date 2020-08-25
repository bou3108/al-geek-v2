package fr.afcepf.algeek.rest;

import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

public class BasicRestCommunicator<T> {

    private RestTemplate restTemplate = new RestTemplate();

    private Class<T> typeObject;
    private Class<T[]> typeArrayObject;

    public BasicRestCommunicator(Class<T> type, Class<T[]> listType) {
        this.typeObject = type;
        this.typeArrayObject = listType;
    }

    public T get(String url) {
        try {
            return restTemplate.getForObject(url, typeObject);
        } catch (Exception ex) {

        }
        return null;
    }

    public List<T> getList(String url) {
        List<T> list = null;
        try {
            T[] array = restTemplate.getForObject(url, typeArrayObject);
            list = Arrays.asList(array);
        } catch (Exception ex) {

        }
        return list;
    }

    public T post(String url, T t) {
        try {
            return restTemplate.postForObject(url, t, typeObject);
        } catch (Exception ex) {

        }
        return null;
    }

    public T put(String url, T t) {
        try {
            restTemplate.put(url, t);
            return t;
        } catch (Exception ex) {

        }
        return null;
    }

    public boolean delete(String url) {
        try {
            restTemplate.delete(url);
            return true;
        } catch (Exception ex) {

        }
        return false;
    }
}
