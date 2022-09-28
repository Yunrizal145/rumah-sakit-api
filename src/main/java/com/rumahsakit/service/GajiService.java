package com.rumahsakit.service;

import com.rumahsakit.model.Dokter;
import com.rumahsakit.model.Perawat;
import com.rumahsakit.model.Staff;
import com.rumahsakit.util.DateUtil;
import io.vertx.core.json.JsonObject;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import javax.ws.rs.core.Response;
import java.util.Optional;

@ApplicationScoped
public class GajiService {

    // Update Gaji Dokter
    @Transactional
    public Response updateGajiDokter(Long dokterId, JsonObject request){
        Optional<Dokter> dokterOptional = Dokter.findByIdOptional(dokterId);
        if (dokterOptional.isEmpty()) {
            return Response.status(Response.Status.BAD_REQUEST).entity("DOKTER_NOUT_FOUND").build();
        }

        Dokter dokter = dokterOptional.get();
        dokter.setGaji(request.getLong("gaji"));
        dokter.setAudit(DateUtil.GetDateTime());

        dokter.persist();

        return Response.ok().entity("DATA_UPDATED").build();
    }


    // Update Gaji Perawat
    @Transactional
    public Response updateGajiPerawat(Long perawatId, JsonObject request){
        Optional<Perawat> perawatOptional = Perawat.findByIdOptional(perawatId);
        if (perawatOptional.isEmpty()) {
            return Response.status(Response.Status.BAD_REQUEST).entity("PERAWAT_NOUT_FOUND").build();
        }

        Perawat perawat = perawatOptional.get();
        perawat.setGaji(request.getLong("gaji"));
        perawat.setAudit(DateUtil.GetDateTime());

        perawat.persist();

        return Response.ok().entity("DATA_UPDATED").build();
    }


    // Update Gaji Staff
    @Transactional
    public Response updateGajiStaff(Long staffId, JsonObject request){
        Optional<Staff> staffOptional = Staff.findByIdOptional(staffId);
        if (staffOptional.isEmpty()) {
            return Response.status(Response.Status.BAD_REQUEST).entity("STAFF_NOUT_FOUND").build();
        }

        Staff staff = staffOptional.get();
        staff.setGaji(request.getLong("gaji"));
        staff.setAudit(DateUtil.GetDateTime());

        staff.persist();

        return Response.ok().entity("DATA_UPDATED").build();
    }
}
