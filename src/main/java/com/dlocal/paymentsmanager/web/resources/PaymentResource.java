package com.dlocal.paymentsmanager.web.resources;

import com.dlocal.paymentsmanager.services.PaymentsService;
import com.dlocal.paymentsmanager.web.model.Payment;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.NONE)
@XmlRootElement(name = "payments")
@Path("/payments")
@Produces(MediaType.APPLICATION_JSON)
public class PaymentResource {

    @Autowired
    private PaymentsService paymentsService;

    @GET
    @Path("/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public Response getPayment(@PathParam("id") String id) {
        Payment paymentFEModel = paymentsService.getPaymentById(id);
        if (paymentFEModel != null) {
            if (paymentFEModel.getAmountUSD() == 0) {
                return Response.status(202).build();
            }
            return Response.status(200).entity(paymentFEModel).build();
        }
        return Response.status(404).build();
    }

    @POST
    @Path("/")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addPayment(Payment payment) {
        try {
            payment = paymentsService.addPayment(payment);
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(null).build();
        }
        return Response.status(Response.Status.CREATED).entity(payment).build();
    }
}
