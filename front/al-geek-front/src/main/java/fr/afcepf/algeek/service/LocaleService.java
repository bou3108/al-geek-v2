package fr.afcepf.algeek.service;


import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

@Component
public class LocaleService {
    private static final String FILE = "carac_fr.properties";
    private Map<String, String> map;
    private boolean ready = false;

    @PostConstruct
    public void init() {
        map = new HashMap<String, String>();

        try (InputStream input = getClass().getClassLoader().getResourceAsStream(FILE)) {
            Properties props = new Properties();

            // load a properties file
            props.load(input);

            for (final String name: props.stringPropertyNames())
                map.put(name, props.getProperty(name));

            ready = true;

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public String getLocaleString(String cle) {
        if (! ready || cle == null) {
            // TODO : log warning
            return cle;
        }

        return map.get(cle);
    }
}
