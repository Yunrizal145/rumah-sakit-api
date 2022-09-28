package com.rumahsakit.controller;

import com.rumahsakit.model.Dokter;
import com.rumahsakit.service.DokterService;
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


@Path("/dokter")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class DokterController {

    @Inject
    DokterService dokterService;

    @GET
    @Path("{dokterId}")
    // Description Swagger
    @Operation(
            operationId = "getDataDokter",
            summary = "By Id Dokter",
            description = "Get Data Dokter By Id"
    )
    @APIResponse(
            responseCode = "200",
            description = "Operation Complete",
            content = @Content(mediaType = MediaType.APPLICATION_JSON)
    )
    public Response getDataDokterById(@PathParam("dokterId") Long dokterId){
        return dokterService.getDataDokterById(dokterId);
    }

    @GET
    @Path("/list")
    // Description Swagger
    @Operation(
            operationId = "getDataDokter",
            summary = "List Pagination Dokter",
            description = "Get List Pagination Data Dokter"
    )
    @APIResponse(
            responseCode = "200",
            description = "Operation Complete",
            content = @Content(mediaType = MediaType.APPLICATION_JSON)
    )
    public Response getListDataDokter(@Parameter(example = "2") @QueryParam("page") Integer page){
        return dokterService.getListDataDokter(page);
    }

    @GET
    @Path("/all")
    // Description Swagger
    @Operation(
            operationId = "getDataDokter",
            summary = "All Data Dokter",
            description = "Get All Data Dokter"
    )
    @APIResponse(
            responseCode = "200",
            description = "Operation Complete",
            content = @Content(mediaType = MediaType.APPLICATION_JSON)
    )
    public Response getAllDataDokter(){
        return dokterService.getAllDataDokter();
    }

    @POST
    @Path("/create")
    // Description Swagger
    @Operation(
            operationId = "createDataDokter",
            summary = "Create Data Dokter",
            description = "Create Data Dokter to add new Data inside the list"
    )
    @APIResponse(
            responseCode = "201",
            description = "Operation Complete",
            content = @Content(mediaType = MediaType.APPLICATION_JSON)
    )
    public Response createDataDokter(
            @RequestBody(
                    description = "Create the Data",
                    required = true,
                    content = @Content(schema = @Schema(implementation = Dokter.class))
            )JsonObject request) {

        return dokterService.createDataDokter(request);
    }


    @POST
    @Path("/filter")
    public Response list(JsonObject request){
        return dokterService.filterDataDokter(request);
    }
}
