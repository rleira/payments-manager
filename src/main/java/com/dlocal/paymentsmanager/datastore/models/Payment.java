package com.dlocal.paymentsmanager.datastore.models;

import com.dlocal.paymentsmanager.datastore.enums.PaymentCurrency;
import com.dlocal.paymentsmanager.datastore.enums.PaymentStatus;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;

public class Payment {

    @Id
    public String id = "";

    public Long timestamp = System.currentTimeMillis() / 1000L;

    @Indexed
    public PaymentCurrency currency;

    @Indexed
    public Double amount;

    @Indexed
    public String transactionId;

    @Indexed
    public String merchantId;

    @Indexed
    public PaymentStatus paymentStatus = PaymentStatus.PENDING;

    public Double amountUSD;

    public Payment() {}

    @Override
    public String toString() {
        return String.format(
                "FixerIO[id='%s', currency='%s', amount='%s, transaction_id='%s, merchant_id='%s, paymentStatus='%s]",
                id,
                currency,
                amount,
                transactionId,
                merchantId,
                paymentStatus
        );
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
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
