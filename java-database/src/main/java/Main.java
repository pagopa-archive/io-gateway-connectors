import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class Main {

    public static JsonObject main(JsonObject jsonArgs) {
        try {
            Args args = new Gson().fromJson(jsonArgs, Args.class);

            Importer importer = new Importer(args);
            if (importer.showForm()) {
                JsonObject form = new FormConfiguration().load();
                return createResponse(form);
            }

            JsonObject data = new MockData().load();
            return createResponse(data);
        } catch (Exception e) {
            return createErrorResponse(e.getMessage());
        }
    }

    public static JsonObject createResponse(JsonElement element){
        JsonObject response = new JsonObject();
        response.add("body", element);
        return response;
    }

    static JsonObject createErrorResponse(String message){
        JsonObject error = new JsonObject();
        error.addProperty("error", message);
        return createResponse(error);
    }
}
