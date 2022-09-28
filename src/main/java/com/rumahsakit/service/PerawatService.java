package com.rumahsakit.service;

import com.google.common.base.Strings;
import com.rumahsakit.model.Dokter;
import com.rumahsakit.model.Perawat;
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
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class PerawatService {

    @Inject
    EntityManager em;

    // Pembuatan Data Perawat
    @Transactional
    public Response createDataPerawat(JsonObject request){

        String namaLengkap = request.getString("nama_lengkap");
        String gender = request.getString("gender");
        String email = request.getString("email");
        String phoneNumber = request.getString("phone_number");
        Long gaji = request.getLong("gaji");
        String time = DateUtil.GetDateTime();

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
        perawat.setAudit(time);

        perawat.persist();

        return Response.ok().status(Response.Status.CREATED).entity("DATA_TERBUAT").build();
    }

    // Pengambilan Semua Data Perawat
    public Response getAllDataPerawat(){
        List<Perawat> perawat = Perawat.findAll().list();
        if (perawat.isEmpty()) {
            return Response.status(Response.Status.BAD_REQUEST).entity("PERAWAT_NOT_FOUND").build();
        }

        JsonObject result = new JsonObject();
        result.put("data", perawat);
        // Pagination
        result.put("total", Perawat.findAll().list().size());
        result.put("totalPage", BasicUtil.roundUp(Perawat.count(), 10));

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
        // Pagination
        result.put("total", Perawat.findAll().list().size());
        result.put("totalPage", BasicUtil.roundUp(Perawat.count(), 10));

        return Response.ok().entity(result).build();
    }

    // Pagination Perawat
    public Response getListDataPerawat(Integer page){
        List<Perawat> perawat = Perawat.findAll().page(page, 10).list();


        JsonObject result = new JsonObject();
        result.put("data", perawat);
        // Pagination
        result.put("total", Perawat.findAll().list().size());
        result.put("totalPage", BasicUtil.roundUp(Perawat.count(), 10));

        return Response.ok().entity(result).build();
    }


    // Filter Data Perawat
    public Response filterDataPerawat(JsonObject request){
        Integer limit = request.getInteger("limit");
        Integer offset = request.getInteger("offset");

        String nama = request.getString("nama_lengkap");
        String email = request.getString("email");
        String phoneNumber = request.getString("phone_number");

        // Implementasi sql
        StringBuilder sb = new StringBuilder();
        sb.append(" SELECT * FROM perawat ");
        sb.append(" WHERE TRUE ");
        if(!Strings.isNullOrEmpty(nama)){
            sb.append(" AND nama_lengkap ILIKE :nama_lengkap ");
        }if(!Strings.isNullOrEmpty(email)){
            sb.append(" AND email ILIKE :email ");
        }if(!Strings.isNullOrEmpty(phoneNumber)){
            sb.append(" AND phone_number ILIKE :phoneNumber ");
        }

        Query query = em.createNativeQuery(sb.toString(), Perawat.class);

        if(!Strings.isNullOrEmpty(nama)){
            query.setParameter("nama_lengkap", nama);
        }if(!Strings.isNullOrEmpty(email)){
            query.setParameter("email", email);
        }if(!Strings.isNullOrEmpty(phoneNumber)){
            query.setParameter("phone_number", phoneNumber);
        }

        query.setFirstResult(offset);
        query.setMaxResults(limit);

        List<Perawat> perawat = query.getResultList();

        JsonObject result = new JsonObject();
        result.put("data", perawat);
        // Pagination
        result.put("total", Perawat.findAll().list().size());
        result.put("totalPage", BasicUtil.roundUp(Perawat.count(), 10));

        return Response.ok().entity(result).build();
    }

}
