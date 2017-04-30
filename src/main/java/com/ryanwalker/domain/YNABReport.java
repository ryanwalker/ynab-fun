package com.ryanwalker.domain;


import com.ryanwalker.rest.api.request.Transaction;

import java.util.Map;
import java.util.TreeMap;

public class YNABReport {
    private Map<String, ReportLineItem> categoryLineItems = new TreeMap<>();
    private Map<String, ReportLineItem> payeeLineItems = new TreeMap<>();

    public void addTransactionToReport(Transaction transaction) {
        //TODO - don't want to do it twice
        handleLineItem(new ReportLineItem(ReportLineItemType.Category, transaction));
        handleLineItem(new ReportLineItem(ReportLineItemType.Payee, transaction));
    }

    private void handleLineItem(ReportLineItem newLineItem) {
        String key;
        Map<String, ReportLineItem> lineItems;
        if (ReportLineItemType.Category == newLineItem.getReportLineItemType()) {
            key = newLineItem.getCategory();
            lineItems = categoryLineItems;
        } else {
            key = newLineItem.getPayee();
            lineItems = payeeLineItems;
        }
        if (lineItems.containsKey(key)) {
            ReportLineItem existingLineItem = lineItems.get(key);
            existingLineItem.setRunningTotal(existingLineItem.getRunningTotal().add(newLineItem.getRunningTotal()));
            existingLineItem.setRunningCount(existingLineItem.getRunningCount() + 1);
        } else {
            lineItems.put(key, newLineItem);
        }
    }

    public String print() {
        StringBuilder reportBuilder = new StringBuilder("Spending By Category:\n");
        for (Map.Entry<String, ReportLineItem> entrySet : categoryLineItems.entrySet()) {
            ReportLineItem lineItem = entrySet.getValue();
            reportBuilder.append(lineItem.getCategory()).append(": ");
            reportBuilder.append(lineItem.getRunningTotal());
            reportBuilder.append(" ");
            reportBuilder.append("(").append(lineItem.getRunningCount()).append(")");
            reportBuilder.append("\n");
        }
        reportBuilder.append("\nSpending By Payee:\n");
        for (Map.Entry<String, ReportLineItem> entrySet : payeeLineItems.entrySet()) {
            ReportLineItem lineItem = entrySet.getValue();
            reportBuilder.append(lineItem.getPayee()).append(": ");
            reportBuilder.append(lineItem.getRunningTotal());
            reportBuilder.append(" ");
            reportBuilder.append("(").append(lineItem.getRunningCount()).append(")");
            reportBuilder.append("\n");
        }
        return reportBuilder.toString();
    }

}