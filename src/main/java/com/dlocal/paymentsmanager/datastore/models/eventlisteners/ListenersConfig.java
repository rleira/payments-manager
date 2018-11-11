package com.dlocal.paymentsmanager.datastore.models.eventlisteners;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ListenersConfig {

    @Bean
    public PaymentEventListener paymentEventListenerEventListener() {
        return new PaymentEventListener();
    }
}
