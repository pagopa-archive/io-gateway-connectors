package domain.salesforce;

import com.google.gson.Gson;

public class SalesforceConfiguration {

    String username;
    String password;
    String salesforceLoginUrl;
    String clientId;
    String clientSecret;

    public String getUsername() {
        return username;
    }


    public String getPassword() {
        return password;
    }

    public String getSalesforceLoginUrl() {
        return salesforceLoginUrl;
    }

    public String getClientId() {
        return clientId;
    }


    public String getClientSecret() {
        return clientSecret;
    }

}
