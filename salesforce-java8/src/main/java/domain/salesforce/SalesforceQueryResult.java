package domain.salesforce;

import domain.salesforce.Record;

import java.util.ArrayList;
import java.util.List;

public class SalesforceQueryResult {
    private int totalSize;
    private String done;
    private ArrayList<Record> records = new ArrayList<Record>();

    public List<Record> getRecords() {
        return  records;
    }

    public int getTotalSize() {
        return totalSize;
    }
}
