package config;

import error.ImportException;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Configuration {

    private static Configuration instance;

    private String databaseType;

    public static Configuration load() {
        if(instance == null){
            instance = new Configuration();
        }
        return instance;
    }

    private Configuration() {
        init();
    }

    private void init() {

        try (InputStream input = this.getClass().getResourceAsStream("/config.properties")) {
            Properties prop = new Properties();
            prop.load(input);

            databaseType = prop.getProperty("database.type");

        } catch (IOException ex) {
            throw new ImportException("Error loading config.properties: "+ex.getMessage(), ex);
        }
    }

    public String getDatabaseType(){
        return databaseType;
    }

}
