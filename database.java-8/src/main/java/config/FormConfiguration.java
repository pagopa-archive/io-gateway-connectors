package config;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import error.ImportException;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class FormConfiguration {

    public JsonObject load(String databaseType) {
        try {
            BufferedReader br = new BufferedReader(
                    new InputStreamReader(this.getClass().getResourceAsStream("/" + databaseType + "/form.json")));
            JsonParser parser = new JsonParser();
            return parser.parse(br).getAsJsonObject();
        } catch (Exception e){
            throw new ImportException("Error reading form configuration", e);
        }
    }
}
