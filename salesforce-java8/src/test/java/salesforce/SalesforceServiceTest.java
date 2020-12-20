package salesforce;

import domain.iosdk.Message;
import domain.salesforce.SalesforceMessage;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import salesforce.SalesforceRepository;
import salesforce.SalesforceService;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class SalesforceServiceTest {

    @Mock
    SalesforceRepository salesforceRepository;

    @Before
    public void setUp(){

    }

    @Test
    public void testloadMessages() throws Exception{
        List<SalesforceMessage> salesforceMessages = new ArrayList<SalesforceMessage>();
        salesforceMessages.add(new SalesforceMessage());
        when(salesforceRepository.getMessages()).thenReturn( salesforceMessages );
        SalesforceService salesforceService = new SalesforceService(salesforceRepository);
        List<Message> messages = salesforceService.loadMessages();
        Assert.assertTrue( messages.size() == salesforceMessages.size());
    }

    @Test(expected = Exception.class)
    public void testRepositoryException() throws Exception {
        SalesforceService salesforceService = new SalesforceService(salesforceRepository);
        when(salesforceRepository.getMessages()).thenThrow(Exception.class);
        List<Message> messages = salesforceService.loadMessages();
    }
}
