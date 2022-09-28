package com.rumahsakit.controller;

import com.rumahsakit.service.PasienService;
import io.vertx.core.json.JsonObject;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/pasien")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PasienController {

    @Inject
    PasienService pasienService;

    @GET
    @Path("/{pasienId}")
    public Response getDataPasienById(Long pasienId){
        return pasienService.getDataPasienById(pasienId);
    }

    @GET
    @Path("/list")
    public Response getListDataPasien(@Parameter(example = "2") @QueryParam("page") Integer page){
        return pasienService.getListDataPasien(page);
    }

    @GET
    @Path("/all")
    public Response getAllDataPasien(){
        return pasienService.getAllDataPasien();
    }

    @POST
    @Path("/create")
    public Response createDataPasien(JsonObject request){
        return pasienService.createDataPasien(request);
    }

    @POST
    @Path("/filter")
    public Response filterDataPasien(JsonObject request){
        return pasienService.filterDataPasien(request);
    }

}
