package com.dlocal.paymentsmanager.datastore.models.eventlisteners;

import com.dlocal.paymentsmanager.datastore.models.Payment;
import com.dlocal.paymentsmanager.services.PaymentsService;
import org.eclipse.jetty.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;

public class PaymentEventListener extends AbstractMongoEventListener<Payment> {

    @Autowired
    private MongoOperations mongoOperations;

    @Autowired
    private PaymentsService paymentsService;

    @Override
    public void onBeforeConvert(BeforeConvertEvent<Payment> event) {
        Payment payment = event.getSource();
        if (payment != null && StringUtil.isBlank(payment.getId())) {
            payment.setId(paymentsService.buildPaymentId(payment));
            mongoOperations.save(payment);
        }
    }
}
