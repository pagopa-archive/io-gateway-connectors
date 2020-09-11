package domain;

import com.google.gson.annotations.SerializedName;

public class Message {

    private int amount;

    @SerializedName("due_date")
    private Long dueDate;

    @SerializedName("fiscal_code")
    private String fiscalCode;

    @SerializedName("invalid_afted_due_date")
    private boolean invalidAfterDueDate;

    private String markdown;

    @SerializedName("notice_number")
    private int noticeNumber;

    private String subject;

    public Message(int amount, Long dueDate, String fiscalCode, boolean invalidAfterDueDate, String markdown, int noticeNumber, String subject) {
        this.amount = amount;
        this.dueDate = dueDate;
        this.fiscalCode = fiscalCode;
        this.invalidAfterDueDate = invalidAfterDueDate;
        this.markdown = markdown;
        this.noticeNumber = noticeNumber;
        this.subject = subject;
    }

    public int getAmount() {
        return amount;
    }

    public Long getDueDate() {
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

    public int getNoticeNumber() {
        return noticeNumber;
    }

    public String getSubject() {
        return subject;
    }
}
