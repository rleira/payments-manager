package com.dlocal.paymentsmanager.scheduler.task;

import com.dlocal.paymentsmanager.datastore.dal.PaymentRepository;
import com.dlocal.paymentsmanager.datastore.enums.PaymentStatus;
import com.dlocal.paymentsmanager.datastore.models.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Iterator;

@Service
public class PaymentProcessTaskFactory {

    @Autowired
    private PaymentRepository paymentsRepository;


    public PaymentProcessRunnable getPaymentProcessRunnable() {
        return new PaymentProcessRunnable();
    }

    private class PaymentProcessRunnable implements Runnable {
        @Override
        public void run() {
            System.out.println("PaymentProcessTask runnnnn!!");
            Iterator<Payment> paymentIterator = paymentsRepository.findPaymentsByPaymentStatus(PaymentStatus.PENDING).iterator();
            while (paymentIterator.hasNext()) {
                System.out.println(paymentIterator.next().toString());
            }
        }
    }
}
