package com.ryanwalker.rest.api.request;

import java.math.BigDecimal;

public class Transaction {
    private String payee;
    private String category;
    private BigDecimal amount;

    public Transaction() {
    }

    public Transaction(String payee, String category, BigDecimal amount) {
        this.payee = payee;
        this.category = category;
        this.amount = amount;
    }

    public String getPayee() {
        return payee;
    }

    public void setPayee(String payee) {
        this.payee = payee;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
