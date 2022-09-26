package com.rumahsakit.controller;

import com.rumahsakit.service.GajiService;
import io.vertx.core.json.JsonObject;

import javax.inject.Inject;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

@Path("/updategaji")
public class GajiController {

    @Inject
    GajiService gajiService;

    // Update Gaji Pegawai oleh User
    @PUT
    @Path("/dokter/{dokterId}")
    public Response updateGajiDokter(@PathParam("dokterId") Long dokterId, JsonObject request){
        return gajiService.updateGajiDokter(dokterId, request);
    }

    @PUT
    @Path("/perawat/{perawatId}")
    public Response updateGajiPerawat(@PathParam("perawatId") Long perawatId, JsonObject request){
        return gajiService.updateGajiPerawat(perawatId, request);
    }

    @PUT
    @Path("/staff/{staffId}")
    public Response updateGajiStaff(@PathParam("staffId") Long staffId, JsonObject request){
        return gajiService.updateGajiStaff(staffId, request);
    }
}
