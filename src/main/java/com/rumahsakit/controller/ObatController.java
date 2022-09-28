package com.rumahsakit.controller;

import com.rumahsakit.service.ObatService;
import io.vertx.core.json.JsonObject;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/obat")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ObatController {

    @Inject
    ObatService obatService;

    @GET
    @Path("/all")
    public Response getAllDataObat(){
        return obatService.getAllDataObat();
    }

    @POST
    @Path("/create")
    public Response createDataObat(JsonObject request){
        return obatService.createDataObat(request);
    }

    @PUT
    @Path("/update/{obatId}")
    public Response updateDataObat(@PathParam("obatId") Long obatId, JsonObject request){
        return obatService.updateDataObat(obatId, request);
    }

    @DELETE
    @Path("/delete/{obatId}")
    public Response deleteDataObatById(@PathParam("obatId") Long obatId){
        return obatService.deleteDataObatById(obatId);
    }

    @POST
    @Path("/filter")
    public Response filterDataObat(JsonObject request){
        return obatService.filterDataObat(request);
    }

    @GET
    @Path("/list/enum")
    public Response getListEnum(){
        return obatService.getListEnum();
    }
}
