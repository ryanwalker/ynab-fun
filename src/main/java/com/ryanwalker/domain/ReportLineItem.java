package com.ryanwalker.domain;

import com.ryanwalker.rest.api.request.Transaction;

import java.math.BigDecimal;

public class ReportLineItem {
    private ReportLineItemType reportLineItemType;
    private String category;
    private String payee;
    private int runningCount;
    private BigDecimal runningTotal;

    public ReportLineItem(ReportLineItemType type, Transaction transaction) {
        this.reportLineItemType = type;
        this.category = transaction.getCategory();
        this.payee = transaction.getPayee();
        this.runningCount = 1;
        this.runningTotal = transaction.getAmount();

    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public ReportLineItemType getReportLineItemType() {
        return reportLineItemType;
    }

    public void setReportLineItemType(ReportLineItemType reportLineItemType) {
        this.reportLineItemType = reportLineItemType;
    }

    public String getPayee() {
        return payee;
    }

    public void setPayee(String payee) {
        this.payee = payee;
    }

    public int getRunningCount() {
        return runningCount;
    }

    public void setRunningCount(int runningCount) {
        this.runningCount = runningCount;
    }

    public BigDecimal getRunningTotal() {
        return runningTotal;
    }

    public void setRunningTotal(BigDecimal runningTotal) {
        //Always have 2 decimal places
        this.runningTotal = runningTotal.setScale(2, BigDecimal.ROUND_HALF_UP);
    }
}