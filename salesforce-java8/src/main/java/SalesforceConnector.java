import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import domain.iosdk.ConfigurationForm;
import domain.iosdk.Message;
import domain.salesforce.SalesforceConfiguration;
import salesforce.SalesforceConfigurationBuilder;
import salesforce.SalesforceRepository;
import salesforce.SalesforceService;

import java.util.List;

public class SalesforceConnector {

    public static JsonObject main(JsonObject jsonObjectPayload) {
        try {
            ConfigurationForm configurationForm = new Gson().fromJson(jsonObjectPayload, ConfigurationForm.class);
            if (jsonObjectPayload == null || configurationForm.isNotComplete()) {
                return buildFormResponse();
            } else {
                SalesforceConfiguration salesforceConfiguration = SalesforceConfigurationBuilder.build(jsonObjectPayload);
                SalesforceRepository salesforceRepository = new SalesforceRepository(salesforceConfiguration);
                SalesforceService salesforceService = new SalesforceService(salesforceRepository);
                List<Message> messages = salesforceService.loadMessages();
                return buildDataResponse(messages);
            }
        } catch (Exception e) {
            return buildErrorResponse("Si è verificato un errore durante il caricamento dei dati");
        }
    }

    protected static JsonObject buildFormResponse() throws Exception{
        JsonObject form = new FormBuilder().build();
        JsonObject jsonObjectResponse = new JsonObject();
        jsonObjectResponse.add("body", form);
        return jsonObjectResponse;
    }

    protected static JsonObject buildDataResponse( List<Message> messages  ){
        JsonElement jsonMessages = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
                .create()
                .toJsonTree(messages);
        JsonObject data = new JsonObject();
        data.add("data", jsonMessages);
        JsonObject jsonObjectResponse = new JsonObject();
        jsonObjectResponse.add("body", data);
        return jsonObjectResponse;
    }

    protected static JsonObject buildErrorResponse( String message  ){
        JsonObject error = new JsonObject();
        error.addProperty("error", message);
        JsonObject jsonObjectResponse = new JsonObject();
        jsonObjectResponse.add("body", error);
        return jsonObjectResponse;
    }



}
