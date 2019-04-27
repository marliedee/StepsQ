package org.pursuit.stepsq.model;

public class QuotesWrapper {

    private String success;
    private int total;

    public QuotesWrapper(String success, int total) {
        this.success = success;
        this.total = total;
    }

    public String getSuccess() {
        return success;
    }

    public int getTotal() {
        return total;
    }
}
