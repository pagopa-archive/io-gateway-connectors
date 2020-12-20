package domain.iosdk;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

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

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public String getFiscalCode() {
        return fiscalCode;
    }

    public void setFiscalCode(String fiscalCode) {
        this.fiscalCode = fiscalCode;
    }

    public boolean isInvalidAfterDueDate() {
        return invalidAfterDueDate;
    }

    public void setInvalidAfterDueDate(boolean invalidAfterDueDate) {
        this.invalidAfterDueDate = invalidAfterDueDate;
    }

    public String getMarkdown() {
        return markdown;
    }

    public void setMarkdown(String markdown) {
        this.markdown = markdown;
    }

    public String getNoticeNumber() {
        return noticeNumber;
    }

    public void setNoticeNumber(String noticeNumber) {
        this.noticeNumber = noticeNumber;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
}
