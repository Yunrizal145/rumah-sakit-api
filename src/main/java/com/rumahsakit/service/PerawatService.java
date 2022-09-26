package com.rumahsakit.service;

import com.rumahsakit.model.Perawat;
import com.rumahsakit.util.BasicUtil;
import com.rumahsakit.util.FormatUtil;
import io.vertx.core.json.JsonObject;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class PerawatService {

    // Pembuatan Data Perawat
    @Transactional
    public Response createDataPerawat(JsonObject request){

        String namaLengkap = request.getString("nama_lengkap");
        String gender = request.getString("gender");
        String email = request.getString("email");
        String phoneNumber = request.getString("phone_number");
        Long gaji = request.getLong("gaji");

        if (!FormatUtil.isStandardNameInput(namaLengkap) || !FormatUtil.isGenderCodeInput(gender)
                || !FormatUtil.isStandardEmailInput(email) || !FormatUtil.isNumericInput(phoneNumber)
                || !FormatUtil.isNumericInput(String.valueOf(gaji))) {
            return Response.status(Response.Status.BAD_REQUEST).entity("BAD_REQUEST").build();
        }

        Perawat perawat = new Perawat();
        perawat.setNamaLengkap(namaLengkap);
        perawat.setGender(gender);
        perawat.setEmail(email);
        perawat.setPhoneNumber(phoneNumber);
        perawat.setGaji(gaji);

        perawat.persist();

        return Response.ok().status(Response.Status.CREATED).entity(perawat).build();
    }

    // Pengambilan Semua Data Perawat
    public Response getAllDataPerawat(){
        List<Perawat> perawat = Perawat.findAll().list();
        if (perawat.isEmpty()) {
            return Response.status(Response.Status.BAD_REQUEST).entity("PERAWAT_NOT_FOUND").build();
        }

        JsonObject result = new JsonObject();
        result.put("data", perawat);
        return Response.ok().entity(result).build();
    }

    // Pengambilan Data Perawat By Id
    public Response getDataPerawatById(Long perawatId){
        Optional<Perawat> perawat = Perawat.findByIdOptional(perawatId);
        if (perawat.isEmpty()) {
            return Response.status(Response.Status.BAD_REQUEST).entity("PERAWAT_NOT_FOUND").build();
        }

        JsonObject result = new JsonObject();
        result.put("data", perawat);
        return Response.ok().entity(result).build();
    }

    // Pagination Perawat
    public Response getListDataPerawat(Integer page){
        List<Perawat> perawat = new ArrayList<>();
        perawat = Perawat.findAll().page(page, 10).list();

        JsonObject result = new JsonObject();
        result.put("data", perawat);
        result.put("total", Perawat.findAll().list().size());
        result.put("totalPage", BasicUtil.roundUp(Perawat.count(), 10));

        return Response.ok().entity(result).build();
    }

}
