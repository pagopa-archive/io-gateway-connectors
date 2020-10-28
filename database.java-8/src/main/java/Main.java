import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import config.Configuration;
import config.FormConfiguration;
import domain.Message;
import importer.Args;
import importer.Importer;

import java.text.DateFormat;
import java.util.List;

public class Main {

    public static JsonObject main(JsonObject jsonArgs) {
        try {
            Args args = new Gson().fromJson(jsonArgs, Args.class);
            args.setDatabaseType(Configuration.load().getDatabaseType());

            Importer importer = new Importer(args);
            if (importer.showForm()) {
                JsonObject form = new FormConfiguration().load(args.getDatabaseType());
                return createResponse(form);
            }

            List<Message> messages = importer.loadMessages();

            JsonElement jsonMessages = new GsonBuilder()
                    .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
                    .create()
                    .toJsonTree(messages);
            return createResponse(createDataResponse(jsonMessages));
        } catch (Exception e) {
            return createErrorResponse(e.getMessage());
        }
    }

    public static JsonObject createResponse(JsonElement element){
        JsonObject response = new JsonObject();
        response.add("body", element);
        return response;
    }

    public static JsonObject createDataResponse(JsonElement element){
        JsonObject response = new JsonObject();
        response.add("data", element);
        return response;
    }

    static JsonObject createErrorResponse(String message){
        JsonObject error = new JsonObject();
        error.addProperty("error", message);
        return createResponse(error);
    }
}
