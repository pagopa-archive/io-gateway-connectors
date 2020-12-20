package domain.salesforce;

import com.google.gson.annotations.SerializedName;

public class SalesforceAuthResponse {
    private String access_token;
    private String instance_url;
    private String id;
    private String token_type;
    private String issued_at;
    private String signature;

    public String getInstanceUrl() {
        return instance_url;
    }

    public String getAccessToken() {
        return access_token;
    }
}
