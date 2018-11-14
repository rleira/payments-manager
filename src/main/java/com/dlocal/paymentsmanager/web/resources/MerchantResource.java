package com.dlocal.paymentsmanager.web.resources;

import com.dlocal.paymentsmanager.services.MerchantService;
import com.dlocal.paymentsmanager.web.model.Payment;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.NONE)
@XmlRootElement(name = "merchants")
@Path("/merchants")
@Produces(MediaType.APPLICATION_JSON)
public class MerchantResource extends BaseResource {

    @Autowired
    private MerchantService merchantService;

    @OPTIONS
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response optionsGetPayment(Payment payment) {
        return buildResponse(Response.status(Response.Status.OK));
    }

    @GET
    @Path("/{id}/balance")
    @Produces({MediaType.APPLICATION_JSON})
    public Response getPayment(@PathParam("id") String id) {
        if (!merchantService.existsMerchant(id)) {
            return Response.status(404).build();
        }
        return buildResponse(Response.status(200).entity(merchantService.getBalance(id)));
    }
}
