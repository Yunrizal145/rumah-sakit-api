package com.rumahsakit.controller;

import com.rumahsakit.service.StaffService;
import io.vertx.core.json.JsonObject;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("staff")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class StaffController {

    @Inject
    StaffService staffService;

    @GET
    @Path("{staffId}")
    public Response getDataStaffById(@PathParam("staffId") Long staffId){
        return staffService.getDataSTaffById(staffId);
    }

    @GET
    @Path("/list")
    public Response getListDataStaff(@Parameter(example = "2") @QueryParam("page") Integer page){
        return staffService.getListDataStaff(page);
    }

    @GET
    @Path("/all")
    public Response getAllDataStaff(){
        return staffService.getAllDataStaff();
    }

    @GET
    @Path("/list/enum")
    public Response getListEnum(){
        return staffService.getListEnum();
    }

    @POST
    @Path("/create")
    public Response createDataStaff(JsonObject request){
        return staffService.createDataStaff(request);
    }
}
