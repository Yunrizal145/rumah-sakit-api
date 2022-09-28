package com.rumahsakit.service;

import com.google.common.base.Strings;
import com.rumahsakit.category.StaffCategory;
import com.rumahsakit.model.Staff;
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
public class StaffService {

    @Inject
    EntityManager em;

    // Pembuatan Data Staff
    @Transactional
    public Response createDataStaff(JsonObject request){
        String namaLengkap = request.getString("nama_lengkap");
        String gender = request.getString("gender");
        String email = request.getString("email");
        String phoneNumber = request.getString("phone_number");
        Long gaji = request.getLong("gaji");
        String time = DateUtil.GetDateTime();

        if (!FormatUtil.isStandardNameInput(namaLengkap) || !FormatUtil.isGenderCodeInput(gender)
                || !FormatUtil.isStandardEmailInput(email) || !FormatUtil.isNumericInput(phoneNumber)
                || !FormatUtil.isNumericInput(String.valueOf(gaji)))
        {
            return Response.status(Response.Status.BAD_REQUEST).entity("BAD_REQUEST").build();
        }

        Staff staff = new Staff();
        staff.setNamaLengkap(namaLengkap);
        staff.setGender(gender);
        staff.setEmail(email);
        staff.setPhoneNumber(phoneNumber);
        staff.setGaji(gaji);
        staff.setAudit(time);

        staff.persist();

        return Response.ok().status(Response.Status.CREATED).entity("DATA_TERBUAT").build();

    }

    // Pengambilan Semua Data Staff
    public Response getAllDataStaff(){
        List<Staff> staff = Staff.findAll().list();
        if (staff.isEmpty()) {
            return Response.status(Response.Status.BAD_REQUEST).entity("PERAWAT_NOT_FOUND").build();
        }

        JsonObject result = new JsonObject();
        result.put("data", staff);
        // Pagination
        result.put("total", Staff.findAll().list().size());
        result.put("totalPage", BasicUtil.roundUp(Staff.count(), 10));

        return Response.ok().entity(result).build();

    }

    // Pengambilan Data Staff By Id
    public Response getDataSTaffById(Long staffId){
        Optional<Staff> staff = Staff.findByIdOptional(staffId);
        if (staff.isEmpty()) {
            return Response.status(Response.Status.BAD_REQUEST).entity("STAFF_NOT_FOUND").build();
        }

        JsonObject result = new JsonObject();
        result.put("data", staff);
        // Pagination
        result.put("total", Staff.findAll().list().size());
        result.put("totalPage", BasicUtil.roundUp(Staff.count(), 10));

        return Response.ok().entity(result).build();
    }

    // Pagination Staff
    public Response getListDataStaff(Integer page){
        List<Staff> staff = Staff.findAll().page(page, 10).list();

        JsonObject result = new JsonObject();
        result.put("data", staff);
        // Pagination
        result.put("total", Staff.findAll().list().size());
        result.put("totalPage", BasicUtil.roundUp(Staff.count(), 10));

        return Response.ok().entity(result).build();
    }

    // Filter Data Staff
    public Response filterDataStaff(JsonObject request){
        Integer limit = request.getInteger("limit");
        Integer offset = request.getInteger("offset");

        String nama = request.getString("nama_lengkap");
        String email = request.getString("email");
        String phoneNumber = request.getString("phone_number");

        //implement sql
        StringBuilder sb = new StringBuilder();
        sb.append(" SELECT * FROM staff ");
        sb.append(" WHERE TRUE ");
        if(!Strings.isNullOrEmpty(nama)){
            sb.append(" AND nama_lengkap ILIKE :nama_lengkap ");
        }if(!Strings.isNullOrEmpty(email)){
            sb.append(" AND email ILIKE :email ");
        }if(!Strings.isNullOrEmpty(phoneNumber)){
            sb.append(" AND phone_number ILIKE :phoneNumber ");
        }

        Query query = em.createNativeQuery(sb.toString(), Staff.class);

        if(!Strings.isNullOrEmpty(nama)){
            query.setParameter("nama_lengkap", nama);
        }if(!Strings.isNullOrEmpty(email)){
            query.setParameter("email", email);
        }if(!Strings.isNullOrEmpty(phoneNumber)){
            query.setParameter("phone_number", phoneNumber);
        }

        query.setFirstResult(offset);
        query.setMaxResults(limit);

        List<Staff> staff = query.getResultList();

        JsonObject result = new JsonObject();
        result.put("data", staff);
        // Pagination
        result.put("total", Staff.findAll().list().size());
        result.put("totalPage", BasicUtil.roundUp(Staff.count(), 10));

        return Response.ok().entity(result).build();
    }

    // Return Data Enum
    public Response getListEnum(){
        StaffCategory[] listEnum = StaffCategory.values();
        return Response.ok().entity(listEnum).build();
    }

}
