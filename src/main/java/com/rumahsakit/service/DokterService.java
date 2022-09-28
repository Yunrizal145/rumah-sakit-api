package com.rumahsakit.service;

import com.google.common.base.Strings;
import com.rumahsakit.model.Dokter;
import com.rumahsakit.util.BasicUtil;
import com.rumahsakit.util.DateUtil;
import com.rumahsakit.util.FormatUtil;
import io.vertx.core.json.JsonObject;

import javax.enterprise.context.ApplicationScoped;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;
import javax.ws.rs.core.Response;
import java.util.*;

@ApplicationScoped
public class DokterService {

    @Inject
    EntityManager em;


    // Pembuatan Data Dokter
    @Transactional
    public Response createDataDokter(JsonObject request) {

        String namaLengkap = request.getString("nama_lengkap");
        String email = request.getString("email");
        String phoneNumber = request.getString("phone_number");
        String spesialis = request.getString("spesialis");
        Long gaji = request.getLong("gaji");
        String time = DateUtil.GetDateTime();

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
        dokter.setAudit(time);

        dokter.persist();

        return Response.ok().status(Response.Status.CREATED).entity("DATA_TERBUAT").build();
    }


    // Pengambilan Semua Data Dokter
    public Response getAllDataDokter(){
        List<Dokter> dokter = Dokter.findAll().list();
        if (dokter.isEmpty()) {
            return Response.status(Response.Status.BAD_REQUEST).entity("DOKTER_NOT_FOUND").build();
        }

        JsonObject result = new JsonObject();
        result.put("data", dokter);
        // Pagination
        result.put("total", Dokter.findAll().list().size());
        result.put("totalPage", BasicUtil.roundUp(Dokter.count(), 10));

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
        // Pagination
        result.put("total", Dokter.findAll().list().size());
        result.put("totalPage", BasicUtil.roundUp(Dokter.count(), 10));

        return Response.ok().entity(result).build();
    }


    // Pagination Dokter
    public Response getListDataDokter(Integer page){
        List<Dokter> dokter = Dokter.findAll().page(page, 10).list();

        JsonObject result = new JsonObject();
        result.put("data", dokter);
        // Pagination
        result.put("total", Dokter.findAll().list().size());
        result.put("totalPage", BasicUtil.roundUp(Dokter.count(), 10));

        return Response.ok().entity(result).build();
    }


    // Filter Data Dokter
    @Transactional
    public Response filterDataDokter(JsonObject request){
        Integer limit = request.getInteger("limit");
        Integer offset = request.getInteger("offset");

        String spesialis = request.getString("spesialis");
        String nama = request.getString("nama_lengkap");
        String email = request.getString("email");
        String phoneNumber = request.getString("phone_number");

        // Impelemntasi sql
        StringBuilder sb = new StringBuilder();
        sb.append(" SELECT * FROM dokter ");
        sb.append(" WHERE TRUE ");
        if(!Strings.isNullOrEmpty(spesialis)){
            sb.append(" AND spesialis_nama ILIKE :spesialis ");
        }if(!Strings.isNullOrEmpty(nama)){
            sb.append(" AND nama_lengkap ILIKE :nama_lengkap ");
        }if(!Strings.isNullOrEmpty(email)){
            sb.append(" AND email ILIKE :email ");
        }if(!Strings.isNullOrEmpty(phoneNumber)){
            sb.append(" AND phone_number ILIKE :phoneNumber ");
        }

        Query query = em.createNativeQuery(sb.toString(), Dokter.class);

        if(!Strings.isNullOrEmpty(spesialis)){
            query.setParameter("spesialis", spesialis);
        }if(!Strings.isNullOrEmpty(nama)){
            query.setParameter("nama_lengkap", nama);
        }if(!Strings.isNullOrEmpty(email)){
            query.setParameter("email", email);
        }if(!Strings.isNullOrEmpty(phoneNumber)){
            query.setParameter("phone_number", phoneNumber);
        }

        query.setFirstResult(offset);
        query.setMaxResults(limit);

        List<Dokter> resultList = query.getResultList();

        JsonObject result = new JsonObject();
        result.put("data", resultList);
        // Pagination
        result.put("total", Dokter.findAll().list().size());
        result.put("totalPage", BasicUtil.roundUp(Dokter.count(), 10));

        return Response.ok().entity(result).build();
    }


}
