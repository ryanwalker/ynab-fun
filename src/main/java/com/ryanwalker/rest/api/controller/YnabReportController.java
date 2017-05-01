package com.ryanwalker.rest.api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ryanwalker.domain.YNABReport;
import com.ryanwalker.rest.api.request.TransactionList;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Date;

@RestController
public class YnabReportController {

    ObjectMapper jsonMapper = new ObjectMapper();

    @RequestMapping(value = "/")
    public String helloYNAB() {
        return "Hello YNAB! <br />" + new Date();
    }

    @RequestMapping(method = RequestMethod.POST, value = "/report", consumes = MediaType.APPLICATION_JSON_VALUE)
    public String greeting(@RequestBody String requestBody) throws IOException {
        TransactionList transactionList = jsonMapper.readValue(requestBody, TransactionList.class);

        YNABReport ynabReport = new YNABReport(transactionList);

        return ynabReport.print();
    }

}
