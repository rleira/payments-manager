package com.dlocal.paymentsmanager.web.model;

import java.util.List;

public class Payments {
    public List<Payment> payments;

    public Payments(List<Payment> payments) {
        this.payments = payments;
    }

    public List<Payment> getPayments() {
        return payments;
    }

    public void setPayments(List<Payment> payments) {
        this.payments = payments;
    }
}
