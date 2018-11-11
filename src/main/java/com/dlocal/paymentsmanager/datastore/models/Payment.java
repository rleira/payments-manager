package com.dlocal.paymentsmanager.datastore.models;

import com.dlocal.paymentsmanager.datastore.PaymentStatus;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;

public class Payment {

    @Id
    public Integer id;

    public String timestamp;

    public String currency;

    public Double amount;

    public String transactionId;

    public String merchantId;

    @Indexed
    public PaymentStatus paymentStatus = PaymentStatus.PENDING;

    public Double amountUSD;

    public Payment() {}

    @Override
    public String toString() {
        return String.format(
                "FixerIO[id='%s', currency='%s', amount='%s, transactionId='%s, merchantId='%s, paymentStatus='%s]",
                id,
                currency,
                amount,
                transactionId,
                merchantId,
                paymentStatus
        );
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }

    public PaymentStatus getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(PaymentStatus paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public Double getAmountUSD() {
        return amountUSD;
    }

    public void setAmountUSD(Double amountUSD) {
        this.amountUSD = amountUSD;
    }
}
