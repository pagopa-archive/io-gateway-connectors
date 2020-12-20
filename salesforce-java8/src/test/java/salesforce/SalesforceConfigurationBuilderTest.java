package salesforce;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import domain.salesforce.SalesforceConfiguration;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import salesforce.SalesforceConfigurationBuilder;

import static org.junit.Assert.assertTrue;

@RunWith(MockitoJUnitRunner.class)
public class SalesforceConfigurationBuilderTest {

    @Before
    public void setUp(){

    }

    @Test(expected = Exception.class)
    public void testNullPayload() throws Exception{
        SalesforceConfiguration salesforceConfiguration = SalesforceConfigurationBuilder.build(null );
    }

    @Test
    public void testPayload() throws Exception{
        String payload = "{ \"salesforceLoginUrl\" : \"https://login.salesforce.com/services/oauth2/token\", \"username\" : \"user\", \"password\" : \"pwd\", \"clientId\" : \"Id\", \"clientSecret\" : \"secret\" }";
        JsonObject jsonObjectPayload = new Gson().fromJson(payload, JsonObject.class);
        SalesforceConfiguration salesforceConfiguration = SalesforceConfigurationBuilder.build(jsonObjectPayload);
        Assert.assertTrue(salesforceConfiguration.getUsername().equals("user"));
        Assert.assertTrue(salesforceConfiguration.getSalesforceLoginUrl().equals("https://login.salesforce.com/services/oauth2/token"));
        Assert.assertTrue(salesforceConfiguration.getClientSecret().equals("secret"));
        Assert.assertTrue(salesforceConfiguration.getClientId().equals("Id"));
    }


}
