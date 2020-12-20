package salesforce;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import domain.salesforce.SalesforceConfiguration;

public class SalesforceConfigurationBuilder {

    public static SalesforceConfiguration build(JsonObject jsonObjectPayload ) throws Exception{
        if (jsonObjectPayload==null) throw new Exception();
        String payload = new Gson().toJson(jsonObjectPayload);
        SalesforceConfiguration salesforceConfiguration = new Gson().fromJson(payload, SalesforceConfiguration.class);
        return salesforceConfiguration;
    }

}
