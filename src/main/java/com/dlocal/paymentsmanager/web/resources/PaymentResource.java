package com.dlocal.paymentsmanager.web.resources;

import com.dlocal.paymentsmanager.datastore.dal.PaymentRepository;
import com.dlocal.paymentsmanager.datastore.models.Payment;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Optional;

@XmlAccessorType(XmlAccessType.NONE)
@XmlRootElement(name = "payments")
@Path("/payments")
@Produces(MediaType.APPLICATION_JSON)
public class PaymentResource {

    @Autowired
    private PaymentRepository repository;

    @GET
    @Path("/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public Response getPayment(@PathParam("id") String id) {
        Optional<Payment> PaymentOpt = repository.findById(id);
        if (PaymentOpt.isPresent()) {
            com.dlocal.paymentsmanager.web.model.Payment paymentFEModel = new com.dlocal.paymentsmanager.web.model.Payment();
            paymentFEModel.setId(PaymentOpt.get().getId());
            paymentFEModel.setDate(PaymentOpt.get().getTimestamp());
            paymentFEModel.setMerchantId(PaymentOpt.get().getMerchantId());
            paymentFEModel.setTransactionId(PaymentOpt.get().getTransactionId());
            paymentFEModel.setAmountUSD(PaymentOpt.get().getAmountUSD());
            paymentFEModel.setPaymentStatus(PaymentOpt.get().getPaymentStatus());

            return Response.status(200).entity(paymentFEModel).build();
        }
        return Response.status(404).build();
    }

    @POST
    @Path("/")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addPayment(com.dlocal.paymentsmanager.web.model.Payment payment) {
        com.dlocal.paymentsmanager.web.model.Payment paymentFEModel = new com.dlocal.paymentsmanager.web.model.Payment();
        paymentFEModel.setId(1234);
        return Response.status(Response.Status.CREATED).entity(paymentFEModel).build();
    }
}
