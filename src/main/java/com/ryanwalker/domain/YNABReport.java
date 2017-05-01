package com.ryanwalker.domain;


import com.ryanwalker.rest.api.request.Transaction;
import com.ryanwalker.rest.api.request.TransactionList;

import java.util.Map;
import java.util.TreeMap;

public class YNABReport {
    private Map<String, ReportLineItem> categoryLineItems = new TreeMap<>();
    private Map<String, ReportLineItem> payeeLineItems = new TreeMap<>();

    public YNABReport(TransactionList transactionList) {
        for (Transaction transaction : transactionList.getTransactions()) {
            this.addTransactionToReport(transaction);
        }
    }

    public void addTransactionToReport(Transaction transaction) {
        //Category data
        Map<String, ReportLineItem> data = this.categoryLineItems;
        String key = transaction.getCategory();
        ReportLineItemType type = ReportLineItemType.Category;
        addReportLineItem(transaction, data, key, type);

        //Payee data
        data = this.payeeLineItems;
        key = transaction.getPayee();
        type = ReportLineItemType.Payee;
        addReportLineItem(transaction, data, key, type);
    }

    private void addReportLineItem(Transaction transaction, Map<String, ReportLineItem> data, String key, ReportLineItemType type) {
        if (data.containsKey(key)) {
            ReportLineItem existingLineItem = data.get(key);
            existingLineItem.setRunningTotal(existingLineItem.getRunningTotal().add(transaction.getAmount()));
            existingLineItem.setRunningCount(existingLineItem.getRunningCount() + 1);
        } else {
            data.put(key, new ReportLineItem(type, transaction));
        }
    }

    public String print() {

        StringBuilder reportBuilder = new StringBuilder();
        reportBuilder.append("==========================\n");
        reportBuilder.append("| Spending By Category:  |\n");
        reportBuilder.append("==========================\n");
        for (Map.Entry<String, ReportLineItem> entrySet : categoryLineItems.entrySet()) {
            ReportLineItem lineItem = entrySet.getValue();
            reportBuilder.append(lineItem.getCategory()).append(": ");
            reportBuilder.append(lineItem.getRunningTotal());
            reportBuilder.append(" ");
            reportBuilder.append("(").append(lineItem.getRunningCount()).append(")");
            reportBuilder.append("\n");
        }
        reportBuilder.append("\n==========================\n");
        reportBuilder.append("| Spending By Payee:     |\n");
        reportBuilder.append("==========================\n");
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