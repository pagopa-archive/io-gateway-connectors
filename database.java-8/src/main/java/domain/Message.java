package domain;

import com.google.gson.annotations.SerializedName;

import java.sql.Date;

public class Message {

    private Integer amount;

    @SerializedName("due_date")
    private Date dueDate;

    @SerializedName("fiscal_code")
    private String fiscalCode;

    @SerializedName("invalid_afted_due_date")
    private boolean invalidAfterDueDate;

    private String markdown;

    @SerializedName("notice_number")
    private String noticeNumber;

    private String subject;

    public Message(Integer amount, Date dueDate, String fiscalCode, boolean invalidAfterDueDate, String markdown, String noticeNumber, String subject) {
        this.amount = amount;
        this.dueDate = dueDate;
        this.fiscalCode = fiscalCode;
        this.invalidAfterDueDate = invalidAfterDueDate;
        this.markdown = markdown;
        this.noticeNumber = noticeNumber;
        this.subject = subject;
    }

    public Integer getAmount() {
        return amount;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public String getFiscalCode() {
        return fiscalCode;
    }

    public boolean getInvalidAfterDueDate() {
        return invalidAfterDueDate;
    }

    public String getMarkdown() {
        return markdown;
    }

    public String getNoticeNumber() {
        return noticeNumber;
    }

    public String getSubject() {
        return subject;
    }
}
