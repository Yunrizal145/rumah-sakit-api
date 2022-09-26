package com.rumahsakit.controller;

import com.rumahsakit.service.UserService;
import io.vertx.core.json.JsonObject;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/user")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserController {

    @Inject
    UserService service;

    @POST
    @Path("/login")
//    @PermitAll
    public Response login(JsonObject request){
        return service.login(request);
    }

    @POST
    @Path("/register")
//    @RolesAllowed("admin")
    public Response register(JsonObject request){
        return service.register(request);
    }
}
