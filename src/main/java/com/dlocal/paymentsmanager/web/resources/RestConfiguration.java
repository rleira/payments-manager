package com.dlocal.paymentsmanager.web.resources;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

@Component
public class RestConfiguration extends ResourceConfig {

    RestConfiguration () {
        register(PaymentResource.class);
    }
}
