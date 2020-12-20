import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class SalesforceConnectorIntegrationTest {

    @Test
    public void testEnd2EndWithoutNullPayload() throws Exception {
        JsonObject jsonObjectResponse = SalesforceConnector.main( null );
        System.out.println( new Gson().toJson(jsonObjectResponse));
        assertNotNull( jsonObjectResponse.get("body") );
        assertNotNull( jsonObjectResponse.get("body").getAsJsonObject().get("form") );
        assertNull( jsonObjectResponse.get("body").getAsJsonObject().get("error") );
        assertNull( jsonObjectResponse.get("body").getAsJsonObject().get("data") );
    }

    @Test
    public void testEnd2EndWithoutEmptyPayload() throws Exception {
        String payload = "{}";
        JsonObject jsonObjectPayload = new Gson().fromJson( payload, JsonObject.class );
        JsonObject jsonObjectResponse = SalesforceConnector.main( null );
        System.out.println( new Gson().toJson(jsonObjectResponse));
        assertNotNull( jsonObjectResponse.get("body") );
        assertNotNull( jsonObjectResponse.get("body").getAsJsonObject().get("form") );
        assertNull( jsonObjectResponse.get("body").getAsJsonObject().get("error") );
        assertNull( jsonObjectResponse.get("body").getAsJsonObject().get("data") );
    }


    @Test
    public void testEnd2EndWithWrongConfiguration() throws Exception {
        String payload = "{\"clientId\":\"\",\"clientSecret\":\"\",\"password\":\"\",\"salesforceLoginUrl\":\"https://login.salesforce.com/services/oauth2/token\",\"username\":\"\"}";
        JsonObject jsonObjectPayload = new Gson().fromJson( payload, JsonObject.class );
        JsonObject jsonObjectResponse = SalesforceConnector.main( jsonObjectPayload );
        System.out.println( new Gson().toJson(jsonObjectResponse));
        assertNotNull( jsonObjectResponse.get("body") );
        assertNotNull( jsonObjectResponse.get("body").getAsJsonObject().get("form") );
        assertNull( jsonObjectResponse.get("body").getAsJsonObject().get("error") );
        assertNull( jsonObjectResponse.get("body").getAsJsonObject().get("data") );
    }

    @Test
    public void testEnd2EndWithCorrectConfiguration() throws Exception {
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(this.getClass().getResourceAsStream("/payload.json")));
        JsonObject jsonObjectPayload = new Gson().fromJson(reader, JsonObject.class);
        JsonObject jsonObjectResponse = SalesforceConnector.main(jsonObjectPayload );
        System.out.println( new Gson().toJson(jsonObjectResponse));
        assertNotNull( jsonObjectResponse.get("body") );
        assertNull( jsonObjectResponse.get("body").getAsJsonObject().get("form") );
        assertNotNull( jsonObjectResponse.get("body").getAsJsonObject().get("data") );
        assertNull( jsonObjectResponse.get("body").getAsJsonObject().get("error") );
    }


}
