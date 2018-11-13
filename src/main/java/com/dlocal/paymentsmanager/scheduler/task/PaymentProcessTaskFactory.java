package com.dlocal.paymentsmanager.scheduler.task;

import com.dlocal.paymentsmanager.datastore.dal.PaymentRepository;
import com.dlocal.paymentsmanager.datastore.enums.PaymentStatus;
import com.dlocal.paymentsmanager.datastore.models.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.util.Iterator;

@Service
public class PaymentProcessTaskFactory {

    @Autowired
    private PaymentRepository paymentsRepository;

    @Autowired
    private Environment env;


    public PaymentProcessRunnable getPaymentProcessRunnable() {
        return new PaymentProcessRunnable();
    }

    private class PaymentProcessRunnable implements Runnable {
        private Double successProbability = Double.parseDouble(env.getProperty("payment.success.probability"));

        @Override
        public void run() {
            System.out.println("PaymentProcessTask running!");
            Iterator<Payment> paymentIterator = paymentsRepository.findPaymentsByPaymentStatus(PaymentStatus.PENDING).iterator();
            while (paymentIterator.hasNext()) {
                Payment payment = paymentIterator.next();
                if (payment.getAmountUSD() != null && payment.getAmountUSD() > 0) {
                    payment.setPaymentStatus(Math.random() < successProbability ? PaymentStatus.PAID : PaymentStatus.REJECTED);
                    paymentsRepository.save(payment);
                    System.out.println("PaymentProcessTask, payment: " + payment);
                }
            }
            System.out.println("PaymentProcessTask ending!");
        }
    }
}
