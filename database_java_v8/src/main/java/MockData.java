import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class MockData {

    public JsonObject load() {
        BufferedReader br = new BufferedReader(
                new InputStreamReader(this.getClass().getResourceAsStream("/mock_data.json")));
        JsonParser parser = new JsonParser();
        return parser.parse(br).getAsJsonObject();
    }
}
