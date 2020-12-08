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
        String payload = "{}";
        JsonObject jsonObjectPayload = new Gson().fromJson( payload, JsonObject.class );
        JsonObject jsonObjectResponse = SalesforceConnector.main( jsonObjectPayload );
        assertNotNull( jsonObjectResponse.get("body") );
        assertNull( jsonObjectResponse.get("body").getAsJsonObject().get("error") );
        assertNull( jsonObjectResponse.get("body").getAsJsonObject().get("data") );
    }

    @Test
    public void testEnd2EndWithoutEmptyPayload() throws Exception {
        String payload = "{}";
        JsonObject jsonObjectPayload = new Gson().fromJson( payload, JsonObject.class );
        JsonObject jsonObjectResponse = SalesforceConnector.main( jsonObjectPayload );
        assertNotNull( jsonObjectResponse.get("body") );
        assertNull( jsonObjectResponse.get("body").getAsJsonObject().get("error") );
        assertNull( jsonObjectResponse.get("body").getAsJsonObject().get("data") );
    }


    @Test
    public void testEnd2EndWithWrongConfiguration() throws Exception {
        String payload = "{clientId: \"test\", clientSecret: \"test\", password: \"test\", salesforceLoginUrl: \"test\", username: \"test\"}";
        JsonObject jsonObjectPayload = new Gson().fromJson( payload, JsonObject.class );
        JsonObject jsonObjectResponse = SalesforceConnector.main( jsonObjectPayload );
        assertNotNull( jsonObjectResponse.get("body") );
        assertNotNull( jsonObjectResponse.get("body").getAsJsonObject().get("error") );
        assertNull( jsonObjectResponse.get("body").getAsJsonObject().get("data") );
    }

    @Test
    public void testEnd2EndWithCorrectConfiguration() throws Exception {
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(this.getClass().getResourceAsStream("/payload.json")));
        JsonObject jsonObjectPayload = new Gson().fromJson(reader, JsonObject.class);
        JsonObject jsonObjectResponse = SalesforceConnector.main(jsonObjectPayload );
        assertNotNull( jsonObjectResponse.get("body") );
        assertNotNull( jsonObjectResponse.get("body").getAsJsonObject().get("data") );
        assertNull( jsonObjectResponse.get("body").getAsJsonObject().get("error") );
    }


}
