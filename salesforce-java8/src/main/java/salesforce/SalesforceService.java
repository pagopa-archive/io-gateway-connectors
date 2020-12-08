package salesforce;

import domain.iosdk.Message;
import domain.salesforce.SalesforceMessage;

import java.util.ArrayList;
import java.util.List;

public class SalesforceService {

    SalesforceRepository salesforceRepository;

    public SalesforceService(SalesforceRepository salesforceRepository) {
        this.salesforceRepository = salesforceRepository;
    }

    public List<Message> loadMessages() throws Exception{
        List<SalesforceMessage> salesforceMessages = salesforceRepository.getMessages();
        List<Message> messages = new ArrayList<Message>();
        for (SalesforceMessage salesforceMessage: salesforceMessages )
        {
            Message message = convertToMessage( salesforceMessage );
            messages.add(message);
        }
        return messages;
    }

    private Message convertToMessage(SalesforceMessage salesforceMessage) {
        Message message = new Message();
        message.setFiscalCode( salesforceMessage.getFiscalCode() );
        message.setSubject( salesforceMessage.getSubject() );
        message.setMarkdown( salesforceMessage.getMarkdown());
        message.setAmount( salesforceMessage.getAmount() );
        message.setDueDate( salesforceMessage.getDueDate() );
        message.setInvalidAfterDueDate( salesforceMessage.isInvalidAfterDueDate() );
        return message;
    }
}
