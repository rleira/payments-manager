package com.dlocal.paymentsmanager.web.resources;

import com.dlocal.paymentsmanager.services.PaymentsService;
import com.dlocal.paymentsmanager.web.model.ErrorModel;
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
public class PaymentResource extends BaseResource {

    @Autowired
    private PaymentsService paymentsService;


    @OPTIONS
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response optionsGetPayment(Payment payment) {
        return buildResponse(Response.status(Response.Status.OK));
    }

    @GET
    @Path("/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public Response getPayment(@PathParam("id") String id) {
        Payment paymentFEModel = paymentsService.getPaymentById(id);
        if (paymentFEModel != null) {
            if (paymentFEModel.getAmount_usd() == null || paymentFEModel.getAmount_usd() == 0) {
                return buildResponse(Response.status(202));
            }
            return buildResponse(Response.status(200).entity(paymentFEModel));
        }
        return buildResponse(Response.status(404));
    }


    @OPTIONS
    @Path("/")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response optionsAddPayment(Payment payment) {
        return buildResponse(Response.status(Response.Status.OK));
    }

    @POST
    @Path("/")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addPayment(Payment payment) {
        try {
            payment = paymentsService.addPayment(payment);
        } catch (Exception e) {
            return buildResponse(Response.status(Response.Status.BAD_REQUEST)
                    .entity(new ErrorModel().setErrorMessage(e.getMessage())));
        }
        return buildResponse(Response.status(Response.Status.CREATED).entity(payment));
    }
}
