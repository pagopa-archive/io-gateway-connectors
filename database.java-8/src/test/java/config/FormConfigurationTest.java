package config;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class FormConfigurationTest {

    @Test
    public void testLoadMysql(){
        JsonObject jsonObject = new FormConfiguration().load("mysql");

        assertNotNull(jsonObject);
        JsonElement form = jsonObject.get("form");
        assertNotNull(form);
    }

    @Test
    public void testLoadOracle(){
        JsonObject jsonObject = new FormConfiguration().load("oracle");

        assertNotNull(jsonObject);
        JsonElement form = jsonObject.get("form");
        assertNotNull(form);
    }
}
