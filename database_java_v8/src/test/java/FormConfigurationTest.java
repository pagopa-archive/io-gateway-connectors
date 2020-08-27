import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class FormConfigurationTest {

    @Test
    public void testLoad(){
        JsonObject jsonObject = new FormConfiguration().load();

        assertNotNull(jsonObject);
        JsonElement form = jsonObject.get("form");
        assertNotNull(form);
    }
}
