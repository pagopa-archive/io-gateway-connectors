package salesforce;

import com.google.gson.Gson;
import domain.salesforce.*;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SalesforceRepository {

    String authToken = null;
    HttpClient httpClient;
    SalesforceConfiguration salesforceConfiguration;

    public SalesforceRepository(SalesforceConfiguration salesforceConfiguration) {
        this.salesforceConfiguration = salesforceConfiguration;
    }

    public List<SalesforceMessage> getMessages() throws Exception {
        SalesforceAuthResponse salesforceAuthResponse = login();
        if (salesforceAuthResponse == null) throw new Exception();
        SalesforceQueryResult salesforceQueryResult = query( salesforceAuthResponse );
        if (salesforceQueryResult == null) throw new Exception();
        List<Record> records = salesforceQueryResult.getRecords();
        List<SalesforceMessage> salesforceMessages = getMessagesFromRecords( salesforceAuthResponse, records );
        return salesforceMessages;
    }

    private List<SalesforceMessage> getMessagesFromRecords( SalesforceAuthResponse salesforceAuthResponse,  List<Record> records) throws Exception{
        if (httpClient == null) this.httpClient = HttpClientBuilder.create().build();
        List<SalesforceMessage> salesforceMessages = new ArrayList<SalesforceMessage>();
        for ( Record record: records )
        {
            HttpGet httpGet = new HttpGet( salesforceAuthResponse.getInstanceUrl() + record.getAttributes().getUrl() );
            httpGet.setHeader("Authorization", "Bearer " + salesforceAuthResponse.getAccessToken());
            try {
                HttpResponse response =  httpClient.execute(httpGet);
                String jsonResponse = EntityUtils.toString(response.getEntity(), "UTF-8");
                SalesforceMessage salesforceMessage = new Gson().fromJson(jsonResponse, SalesforceMessage.class);
                salesforceMessages.add(salesforceMessage);
            } catch (IOException e) {
                throw new Exception("Si è verificato un errore durante la lettura del messaggio:" + record.getAttributes().getUrl() );
            }
        }
        return salesforceMessages;
    }

    protected SalesforceAuthResponse login() throws Exception {
        SalesforceAuthResponse salesforceAuthResponse = null;
        try {
            if (httpClient == null) this.httpClient = HttpClientBuilder.create().build();
            HttpPost httpPost = new HttpPost(salesforceConfiguration.getSalesforceLoginUrl());
            httpPost.setHeader("Content-Type", "application/x-www-form-urlencoded");
            List<NameValuePair> nvps = new ArrayList<NameValuePair>();
            nvps.add(new BasicNameValuePair("client_id", salesforceConfiguration.getClientId()));
            nvps.add(new BasicNameValuePair("client_secret", salesforceConfiguration.getClientSecret()));
            nvps.add(new BasicNameValuePair("username", salesforceConfiguration.getUsername()));
            nvps.add(new BasicNameValuePair("password", salesforceConfiguration.getPassword()));
            nvps.add(new BasicNameValuePair("grant_type", "password"));
            httpPost.setEntity(new UrlEncodedFormEntity(nvps, "UTF-8"));
            HttpResponse response = httpClient.execute(httpPost);
            String jsonResponse = EntityUtils.toString(response.getEntity(), "UTF-8");
            salesforceAuthResponse = new Gson().fromJson(jsonResponse, SalesforceAuthResponse.class);
        } catch (Exception e) {
            throw new Exception("Si è verificato un errore durante l'autenticazione" );
        }
        return salesforceAuthResponse;
    }

    protected SalesforceQueryResult query( SalesforceAuthResponse salesforceAuthResponse ) throws Exception {
        SalesforceQueryResult salesforceQueryResult = null;
        if (httpClient == null) this.httpClient = HttpClientBuilder.create().build();
        HttpGet httpGet = new HttpGet( salesforceAuthResponse.getInstanceUrl() + "/services/data/v20.0/query?q=SELECT+Id,Name,fiscalCode__c,subject__c,markdown__c,dueDate__c,amount__c,invalidAfterDueDate__c+from+Message__c");
        httpGet.setHeader("Authorization", "Bearer " + salesforceAuthResponse.getAccessToken());
        try {
            HttpResponse response =  httpClient.execute(httpGet);
            String jsonResponse = EntityUtils.toString(response.getEntity(), "UTF-8");
            salesforceQueryResult = new Gson().fromJson(jsonResponse, SalesforceQueryResult.class);
        } catch (IOException e) {
            throw new Exception("Si è verificato un errore durante il recupero dei messaggi" );
        }
        return salesforceQueryResult;
    }

    public void setHttpClient(HttpClient httpClient) {
        this.httpClient = httpClient;
    }

}
