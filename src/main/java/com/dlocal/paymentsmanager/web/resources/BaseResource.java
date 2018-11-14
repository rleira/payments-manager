package com.dlocal.paymentsmanager.web.resources;

import javax.ws.rs.core.Response;

public class BaseResource {

    protected Response buildResponse(Response.ResponseBuilder responseBuilder){
        return responseBuilder
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Credentials", "true")
                .header("Access-Control-Allow-Headers", "DNT, X-CustomHeader, Keep-Alive, User-Agent, " +
                        "X-Requested-With, If-Modified-Since, Cache-Control, Content-Type, Content-Range, Range, " +
                        "origin, accept, authorization")
                .header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD")
                .header("Accept", "application/json")
                .build();
    }

}
