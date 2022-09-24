package com.rumahsakit.controller;

import com.rumahsakit.service.UserService;
import io.vertx.core.json.JsonObject;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/user")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@PermitAll
public class UserController {

    @Inject
    UserService service;

    @POST
    @Path("/login")
    @RolesAllowed({"User", "Admin"})
    public Response login(JsonObject request){
        return service.login(request);
    }

    @POST
    @Path("/register")
    public Response register(JsonObject request){
        return service.register(request);
    }

}
