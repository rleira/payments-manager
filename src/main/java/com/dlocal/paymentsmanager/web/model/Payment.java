package com.dlocal.paymentsmanager.web.model;

import com.dlocal.paymentsmanager.datastore.enums.PaymentCurrency;
import com.dlocal.paymentsmanager.datastore.enums.PaymentStatus;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Payment {
    public String id;

    public String date;

    public PaymentCurrency currency;

    public Double amount;

    public String transaction_id;

    public String merchant_id;

    public PaymentStatus paymentStatus = PaymentStatus.PENDING;

    public Double amount_usd;

    public Payment() {}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public PaymentCurrency getCurrency() {
        return currency;
    }

    public void setCurrency(PaymentCurrency currency) {
        this.currency = currency;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getTransactionId() {
        return transaction_id;
    }

    public void setTransactionId(String transactionId) {
        this.transaction_id = transactionId;
    }

    public String getMerchantId() {
        return merchant_id;
    }

    public void setMerchantId(String merchantId) {
        this.merchant_id = merchantId;
    }

    public PaymentStatus getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(PaymentStatus paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public Double getAmountUSD() {
        return amount_usd;
    }

    public void setAmountUSD(Double amountUSD) {
        this.amount_usd = amountUSD;
    }
}
