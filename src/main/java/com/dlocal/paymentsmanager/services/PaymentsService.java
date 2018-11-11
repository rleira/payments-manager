package com.dlocal.paymentsmanager.services;

import com.dlocal.paymentsmanager.datastore.dal.PaymentRepository;
import com.dlocal.paymentsmanager.datastore.enums.PaymentCurrency;
import com.dlocal.paymentsmanager.datastore.models.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PaymentsService {

    @Autowired
    private PaymentRepository paymentsRepository;

    @Autowired
    private MerchantService merchantService;

    public com.dlocal.paymentsmanager.web.model.Payment getPaymentById(String paymentId) {
        Optional<Payment> PaymentOpt = paymentsRepository.findById(paymentId);
        if (PaymentOpt.isPresent()) {
            return paymentToPaymentFE(PaymentOpt.get());
        }
        return null;
    }

    public com.dlocal.paymentsmanager.web.model.Payment addPayment(
            com.dlocal.paymentsmanager.web.model.Payment paymentFEModel
    ) throws Exception{
        if (!merchantService.existsMerchant(paymentFEModel.getMerchantId())) {
            throw new Exception("Merchant does not exist");
        }
        Payment newPayment = paymentFEToPayment(paymentFEModel);
        newPayment = paymentsRepository.save(newPayment);
        paymentFEModel.setId(newPayment.getId());
        return paymentFEModel;
    }


    private com.dlocal.paymentsmanager.web.model.Payment paymentToPaymentFE (Payment payment) {
        com.dlocal.paymentsmanager.web.model.Payment paymentFEModel = new com.dlocal.paymentsmanager.web.model.Payment();
        paymentFEModel.setId(payment.getId());
        paymentFEModel.setDate(payment.getTimestamp());
        paymentFEModel.setMerchantId(payment.getMerchantId());
        paymentFEModel.setTransactionId(payment.getTransactionId());
        paymentFEModel.setAmountUSD(payment.getAmountUSD());
        paymentFEModel.setPaymentStatus(payment.getPaymentStatus());

        return paymentFEModel;
    }

    private Payment paymentFEToPayment (com.dlocal.paymentsmanager.web.model.Payment paymentFEModel) {
        Payment payment = new Payment();

        payment.setCurrency(paymentFEModel.getCurrency());
        payment.setAmount(paymentFEModel.getAmount());
        payment.setMerchantId(paymentFEModel.getMerchantId());
        payment.setTransactionId(paymentFEModel.getTransactionId());

        if (PaymentCurrency.USD.equals(paymentFEModel.getCurrency())) {
            payment.setAmountUSD(paymentFEModel.getAmountUSD());
        }

        return payment;
    }
}
