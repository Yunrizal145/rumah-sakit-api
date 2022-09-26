package com.rumahsakit.service;

import com.rumahsakit.model.Dokter;
import com.rumahsakit.util.BasicUtil;
import com.rumahsakit.util.DateUtil;
import com.rumahsakit.util.FormatUtil;
import io.vertx.core.json.JsonObject;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import javax.ws.rs.core.Response;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class DokterService {

    // Pembuatan Data Dokter
    @Transactional
    public Response createDataDokter(JsonObject request) throws ParseException {

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:MM:ss");

        String namaLengkap = request.getString("nama_lengkap");
        String email = request.getString("email");
        String phoneNumber = request.getString("phone_number");
        String spesialis = request.getString("spesialis");
        Long gaji = request.getLong("gaji");
        Date time = dateFormat.parse(DateUtil.GetDateTime());

        if (!FormatUtil.isStandardNameInput(namaLengkap) || !FormatUtil.isStandardEmailInput(email)
            || !FormatUtil.isNumericInput(phoneNumber) || !FormatUtil.isStandardAlphabetInput(spesialis)
            || !FormatUtil.isNumericInput(String.valueOf(gaji))) {
            return Response.status(Response.Status.BAD_REQUEST).entity("BAD_REQUEST").build();
        }

        Dokter dokter = new Dokter();
        dokter.setNamaLengkap(namaLengkap);
        dokter.setEmail(email);
        dokter.setPhoneNumber(phoneNumber);
        dokter.setSpesialisNama(spesialis);
        dokter.setGaji(gaji);


        dokter.persist();

        return Response.ok().status(Response.Status.CREATED).entity(dokter).build();
    }


    // Pengambilan Semua Data Dokter
    public Response getAllDataDokter(){
        List<Dokter> dokter = Dokter.findAll().list();
        if (dokter.isEmpty()) {
            return Response.status(Response.Status.BAD_REQUEST).entity("DOKTER_NOT_FOUND").build();
        }

        JsonObject result = new JsonObject();
        result.put("data", dokter);
        return Response.ok().entity(result).build();
    }


    // Pengambilan Data Dokter By Id
    public Response getDataDokterById(Long dokterId){
        Optional<Dokter> dokter = Dokter.findByIdOptional(dokterId);
        if (dokter.isEmpty()) {
            return Response.status(Response.Status.BAD_REQUEST).entity("DOKTER_NOT_FOUND").build();
        }

        JsonObject result = new JsonObject();
        result.put("data", dokter);
        return Response.ok().entity(result).build();
    }


    // Pagination Dokter
    public Response getListDataDokter(Integer page){
        List<Dokter> dokter = new ArrayList<>();
        dokter = Dokter.findAll().page(page, 10).list();

        JsonObject result = new JsonObject();
        result.put("data", dokter);
        result.put("total", Dokter.findAll().list().size());
        result.put("totalPage", BasicUtil.roundUp(Dokter.count(), 10));

        return Response.ok().entity(result).build();
    }





}
