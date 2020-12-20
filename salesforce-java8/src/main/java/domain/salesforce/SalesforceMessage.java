package domain.salesforce;

import domain.salesforce.Attributes;

import java.util.Date;

public class SalesforceMessage {

    private Attributes attributes;
    private String Id;
    private String OwnerId;
    private boolean IsDeleted;
    private String Name;
    private Date CreatedDate;
    private String CreatedById;
    private Date LastModifiedDate;
    private String LastModifiedById;
    private Date SystemModstamp;
    private Integer amount__c;
    private Date dueDate__c;
    private boolean invalidAfterDueDate__c;
    private String markdown__c;
    private String noticeNumber__c;
    private String subject__c;
    private String fiscalCode__c;

    public String getId() {
        return Id;
    }

    public String getName() {
        return Name;
    }

    public Integer getAmount() {
        return amount__c;
    }

    public Date getDueDate() {
        return dueDate__c;
    }

    public boolean isInvalidAfterDueDate() {
        return invalidAfterDueDate__c;
    }

    public String getMarkdown() {
        return markdown__c;
    }

    public String getNoticeNumber() {
        return noticeNumber__c;
    }

    public String getSubject() {
        return subject__c;
    }

    public String getFiscalCode() {
        return fiscalCode__c;
    }
}
