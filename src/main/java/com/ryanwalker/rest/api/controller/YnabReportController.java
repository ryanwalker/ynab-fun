package com.ryanwalker.rest.api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ryanwalker.domain.YNABReport;
import com.ryanwalker.rest.api.request.Transaction;
import com.ryanwalker.rest.api.request.TransactionList;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
public class YnabReportController {

    ObjectMapper mapper = new ObjectMapper();

    @RequestMapping(method = RequestMethod.POST, value = "/report", consumes = MediaType.APPLICATION_JSON_VALUE)
    public String greeting(@RequestBody String requestBody) throws IOException {
        TransactionList transactionList = mapper.readValue(requestBody, TransactionList.class);


//        List<Transaction> transactions = new ArrayList<>();
//        transactions = Arrays.asList(
//                new Transaction("Walmart", "Groceries", new BigDecimal(-50)),
//                new Transaction("Starbucks", "Restaurants", new BigDecimal(-5)),
//                new Transaction("Fancy Steak Place", "Restaurants", new BigDecimal(-75)),
//                new Transaction("Work", "Income", new BigDecimal(200)),
//                new Transaction("Work", "Income", new BigDecimal(677))
//        );

        YNABReport ynabReport = new YNABReport();

        for (Transaction transaction : transactionList.getTransactions()) {
            ynabReport.addTransactionToReport(transaction);
        }
        return ynabReport.print();
//        return requestBody;
    }

}
