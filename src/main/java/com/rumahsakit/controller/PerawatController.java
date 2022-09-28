package com.rumahsakit.controller;

import com.rumahsakit.model.Perawat;
import com.rumahsakit.service.PerawatService;
import io.vertx.core.json.JsonObject;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("perawat")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PerawatController {

    @Inject
    PerawatService perawatService;

    @GET
    @Path("{perawatId}")
    // Description swagger
    @Operation(
            operationId = "getDataPerawat",
            summary = "By Id Perawat",
            description = "Get Data Perawat By Id"
    )
    @APIResponse(
            responseCode = "200",
            description = "Operation Complete",
            content = @Content(mediaType = MediaType.APPLICATION_JSON)
    )
    public Response getDataPerawatById(@PathParam("perawatId") Long perawatId){
        return perawatService.getDataPerawatById(perawatId);
    }

    @GET
    @Path("/list")
    // Description swagger
    @Operation(
            operationId = "getDataPerawat",
            summary = "List Pagination Perawat",
            description = "Get List Data Perawat and Pagination"
    )
    @APIResponse(
            responseCode = "200",
            description = "Operation Complete",
            content = @Content(mediaType = MediaType.APPLICATION_JSON)
    )
    public Response getListDataPerawat(@Parameter(example = "2") @QueryParam("page") Integer page){
        return perawatService.getListDataPerawat(page);
    }

    @GET
    @Path("/all")
    // Description swagger
    @Operation(
            operationId = "getDataPerawat",
            summary = "All Data Perawat",
            description = "All Data Perawat"
    )
    @APIResponse(
            responseCode = "200",
            description = "Operation Complete",
            content = @Content(mediaType = MediaType.APPLICATION_JSON)
    )
    public Response getAllDataPerawat(){
        return perawatService.getAllDataPerawat();
    }

    @POST
    @Path("/create")
    // Description swagger
    @Operation(
            operationId = "createDataPerawat",
            summary = "Create Data Perawat",
            description = "Create Data Perawat to add new Data inside the list"
    )
    @APIResponse(
            responseCode = "201",
            description = "Operation Complete",
            content = @Content(mediaType = MediaType.APPLICATION_JSON)
    )
    public Response createDataPerawat(
            @RequestBody(
                    description = "Create the Data",
                    required = true,
                    content = @Content(schema = @Schema(implementation = Perawat.class))
            )JsonObject request){
        return perawatService.createDataPerawat(request);
    }

    @POST
    @Path("/filter")
    public Response filterDataPerawat(JsonObject request){
        return perawatService.filterDataPerawat(request);
    }

}
