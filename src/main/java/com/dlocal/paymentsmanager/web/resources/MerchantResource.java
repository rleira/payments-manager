package com.dlocal.paymentsmanager.web.resources;

import com.dlocal.paymentsmanager.services.MerchantService;
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
public class MerchantResource {

    @Autowired
    private MerchantService merchantService;

    @GET
    @Path("/{id}/balance")
    @Produces({MediaType.APPLICATION_JSON})
    public Response getPayment(@PathParam("id") String id) {
        if (!merchantService.existsMerchant(id)) {
            return Response.status(404).build();
        }
        return Response.status(200).entity(merchantService.getBalance(id)).build();
    }
}
