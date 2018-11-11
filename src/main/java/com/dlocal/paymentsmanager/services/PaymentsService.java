package com.dlocal.paymentsmanager.services;

import com.dlocal.paymentsmanager.datastore.dal.PaymentRepository;
import com.dlocal.paymentsmanager.datastore.models.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PaymentsService {

    @Autowired
    private PaymentRepository paymentsRepository;

    public com.dlocal.paymentsmanager.web.model.Payment getPaymentFromId(String paymentId) {
        Optional<Payment> PaymentOpt = paymentsRepository.findById(paymentId);
        if (PaymentOpt.isPresent()) {
            com.dlocal.paymentsmanager.web.model.Payment paymentFEModel = new com.dlocal.paymentsmanager.web.model.Payment();
            paymentFEModel.setId(PaymentOpt.get().getId());
            paymentFEModel.setDate(PaymentOpt.get().getTimestamp());
            paymentFEModel.setMerchantId(PaymentOpt.get().getMerchantId());
            paymentFEModel.setTransactionId(PaymentOpt.get().getTransactionId());
            paymentFEModel.setAmountUSD(PaymentOpt.get().getAmountUSD());
            paymentFEModel.setPaymentStatus(PaymentOpt.get().getPaymentStatus());

            return paymentFEModel;
        }
        return null;
    }

}
