package com.rumahsakit.service;

import com.google.common.base.Strings;
import com.rumahsakit.category.StaffCategory;
import com.rumahsakit.model.Pasien;
import com.rumahsakit.util.BasicUtil;
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
public class PasienService {

    @Inject
    EntityManager em;


    // Membuat Data Pasien
    @Transactional
    public Response createDataPasien(JsonObject request){

        String namaLengkap = request.getString("nama_lengkap");
        String gender = request.getString("gender");
        String email = request.getString("email");
        String phoneNumber = request.getString("phone_number");
        String status = StaffCategory.JANITOR.name();
        String address = request.getString("address");
        Boolean isCoverBPJS = request.getBoolean("is_cover_bpjs");

        if (!FormatUtil.isStandardNameInput(namaLengkap) || !FormatUtil.isGenderCodeInput(gender)
                || !FormatUtil.isStandardEmailInput(email) || !FormatUtil.isNumericInput(phoneNumber)
                || !FormatUtil.isStandardAlphabetInput(address)) {
            return Response.status(Response.Status.BAD_REQUEST).entity("BAD_REQUEST").build();
        }

        Pasien pasien = new Pasien();
        pasien.setNamaLengkap(namaLengkap);
        pasien.setGender(gender);
        pasien.setEmail(email);
        pasien.setPhoneNumber(phoneNumber);
        pasien.setStatus(status);
        pasien.setAddress(address);
        pasien.setIsCoverBpjs(isCoverBPJS);

        pasien.persist();

        return Response.ok().entity("DATA_PASIEN_CREATED").build();

    }

    // Pengambilan Semua Data Pasien
    public Response getAllDataPasien(){
        List<Pasien> pasien = Pasien.findAll().list();
        if (pasien.isEmpty()) {
            return Response.status(Response.Status.BAD_REQUEST).entity("PASIEN_NOT_FOUND").build();
        }

        JsonObject result = new JsonObject();
        result.put("data", pasien);
        // Pagination
        result.put("total", Pasien.findAll().list().size());
        result.put("totalPage", BasicUtil.roundUp(Pasien.count(), 10));

        return Response.ok().entity(result).build();
    }

    // Pengambilan Data Pasien By Id
    public Response getDataPasienById(Long pasienId){
        Optional<Pasien> pasien = Pasien.findByIdOptional(pasienId);
        if (pasien.isEmpty()) {
            return Response.status(Response.Status.BAD_REQUEST).entity("PASIEN_NOT_FOUND").build();
        }

        JsonObject result = new JsonObject();
        result.put("data", pasien);
        // Pagination
        result.put("total", Pasien.findAll().list().size());
        result.put("totalPage", BasicUtil.roundUp(Pasien.count(), 10));

        return Response.ok().entity(result).build();
    }

    // Pagination Pasien
    public Response getListDataPasien(Integer page){
        List<Pasien> pasien = Pasien.findAll().page(page, 10).list();

        JsonObject result = new JsonObject();
        result.put("data", pasien);
        result.put("total", Pasien.findAll().list().size());
        result.put("totalPage", BasicUtil.roundUp(Pasien.count(), 10));

        return Response.ok().entity(result).build();
    }


    // Filter Data Pasien
    public Response filterDataPasien(JsonObject request){
        Integer limit = request.getInteger("limit");
        Integer offset = request.getInteger("offset");

        String nama = request.getString("nama_lengkap");
        String email = request.getString("email");
        String phoneNumber = request.getString("phone_number");

        // Implementasi sql
        StringBuilder sb = new StringBuilder();
        sb.append(" SELECT * FROM pasien ");
        sb.append(" WHERE TRUE ");
        if(!Strings.isNullOrEmpty(nama)){
            sb.append(" AND nama_lengkap ILIKE :nama_lengkap ");
        }if(!Strings.isNullOrEmpty(email)){
            sb.append(" AND email ILIKE :email ");
        }if(!Strings.isNullOrEmpty(phoneNumber)){
            sb.append(" AND phone_number ILIKE :phoneNumber ");
        }

        Query query = em.createNativeQuery(sb.toString(), Pasien.class);

        if(!Strings.isNullOrEmpty(nama)){
            query.setParameter("nama_lengkap", nama);
        }if(!Strings.isNullOrEmpty(email)){
            query.setParameter("email", email);
        }if(!Strings.isNullOrEmpty(phoneNumber)){
            query.setParameter("phone_number", phoneNumber);
        }

        query.setFirstResult(offset);
        query.setMaxResults(limit);

        List<Pasien> pasien = query.getResultList();

        JsonObject result = new JsonObject();
        result.put("data", pasien);
        // Pagination
        result.put("total", Pasien.findAll().list().size());
        result.put("totalPage", BasicUtil.roundUp(Pasien.count(), 10));

        return Response.ok().entity(result).build();
    }

}
