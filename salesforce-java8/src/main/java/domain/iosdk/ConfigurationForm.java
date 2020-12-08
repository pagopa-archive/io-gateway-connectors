package domain.iosdk;

public class ConfigurationForm {

    String username;
    String password;
    String clientId;
    String clientSecret;
    String salesforceLoginUrl;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }

    public String getSalesforceLoginUrl() {
        return salesforceLoginUrl;
    }

    public void setSalesforceLoginUrl(String salesforceLoginUrl) {
        this.salesforceLoginUrl = salesforceLoginUrl;
    }

    public boolean isNotComplete(){
        return ( this.getClientId() == null
                || this.getClientSecret() == null
                || this.getUsername() == null
                || this.getPassword() == null
                || this.getSalesforceLoginUrl() == null
        );
    }
}
