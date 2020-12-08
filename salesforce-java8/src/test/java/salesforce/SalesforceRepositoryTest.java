package salesforce;

import domain.salesforce.SalesforceAuthResponse;
import domain.salesforce.SalesforceConfiguration;
import org.apache.http.*;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import salesforce.SalesforceRepository;

@RunWith(MockitoJUnitRunner.class)
public class SalesforceRepositoryTest {

    @Mock
    SalesforceConfiguration salesforceConfiguration;
    @Mock HttpClient httpClient;
    @Mock HttpResponse httpResponse;
    @Mock StatusLine statusLine;
    @Mock HttpEntity httpEntity;
    @Mock HttpGet httpGet;

    @Before
    public void setUp(){

    }


    @Test(expected = Exception.class)
    public void testLoginWrongAnswer() throws Exception {
        SalesforceRepository salesforceRepository = new SalesforceRepository( salesforceConfiguration );
        SalesforceAuthResponse salesforceAuthResponse = salesforceRepository.login();
        Assert.assertNull( salesforceAuthResponse );
    }

    @Test(expected = Exception.class)
    public void testLoginException() throws Exception{
        SalesforceRepository salesforceRepository = new SalesforceRepository( salesforceConfiguration );
        SalesforceAuthResponse salesforceAuthResponse = salesforceRepository.login();
        Assert.assertNull( salesforceAuthResponse );
        salesforceRepository.getMessages();
    }

    @Test(expected = Exception.class)
    public void testLoginHttpException() throws Exception{
        SalesforceRepository salesforceRepository = new SalesforceRepository( salesforceConfiguration );
        SalesforceAuthResponse salesforceAuthResponse = salesforceRepository.login();
        Assert.assertNull( salesforceAuthResponse );
        salesforceRepository.getMessages();
    }



}
