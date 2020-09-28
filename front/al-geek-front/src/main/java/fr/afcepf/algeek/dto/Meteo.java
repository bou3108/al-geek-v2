package fr.afcepf.algeek.dto;

import java.io.Serializable;

public class Meteo implements Serializable {

    private String meteoId;
    private String zipcode;
    private String city;
    private String description;
    private String photo;
    private String temperature;

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }



    public Meteo() {
    }

    public Meteo(String meteoId, String zipcode, String city, String description, String photo, String temperature) {
        this.meteoId = meteoId;
        this.zipcode = zipcode;
        this.city = city;
        this.description = description;
        this.photo = photo;
        this.temperature = temperature;
    }

    public String getMeteoId() {
        return meteoId;
    }

    public void setMeteoId(String meteoId) {
        this.meteoId = meteoId;
    }
    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
}
