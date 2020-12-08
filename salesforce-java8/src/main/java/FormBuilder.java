import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class FormBuilder {

    private static String jsonform = " ";


    public JsonObject build() throws Exception {
        try {
            // create a reader
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(this.getClass().getResourceAsStream("/form.json")));
            JsonObject form = new Gson().fromJson(reader, JsonObject.class);
            return form;
        } catch (Exception e){
            throw new Exception("Error parsing the form file");
        }

    }
}
