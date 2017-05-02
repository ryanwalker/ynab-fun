package com.ryanwalker.domain;

import com.ryanwalker.rest.api.request.Transaction;
import com.ryanwalker.rest.api.request.TransactionList;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class YNABReportTest {


    YNABReport report;



    @Test
    public void addTransactionToReport() throws Exception {

        Transaction transaction1 = new Transaction();
        transaction1.setAmount(new BigDecimal(12.12));
        transaction1.setCategory("Restaurants");
        transaction1.setPayee("Smashburger");

        Transaction transaction2 = new Transaction();
        transaction2.setAmount(new BigDecimal(13.88));
        transaction2.setCategory("Restaurants");
        transaction2.setPayee("Smashburger");

        List<Transaction> transactions = Arrays.asList(
                transaction1,
                transaction2
        );
        TransactionList transactionList = new TransactionList();
        transactionList.setTransactions(transactions);
        report = new YNABReport(transactionList);

        String report = this.report.print();
        String expected =
                "==========================\n" +
                "| Spending By Category:  |\n" +
                "==========================\n" +
                "Restaurants: 26.00 (2)\n" +
                "\n" +
                "==========================\n" +
                "| Spending By Payee:     |\n" +
                "==========================\n" +
                "Smashburger: 26.00 (2)\n";

        Assert.assertEquals(expected, report);

    }

}