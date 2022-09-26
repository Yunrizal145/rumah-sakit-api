package com.rumahsakit.service;

import com.rumahsakit.category.StaffCategory;
import com.rumahsakit.model.Staff;
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
public class StaffService {

    // Pembuatan Data Staff
    @Transactional
    public Response createDataStaff(JsonObject request){
        String namaLengkap = request.getString("nama_lengkap");
        String gender = request.getString("gender");
        String email = request.getString("email");
        String phoneNumber = request.getString("phone_number");
        Long gaji = request.getLong("gaji");
        String status = StaffCategory.SECURITY.name();

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
        staff.setStatus(status);

        staff.persist();

        return Response.ok().status(Response.Status.CREATED).entity(staff).build();

    }

    // Pengambilan Semua Data Staff
    public Response getAllDataStaff(){
        List<Staff> staff = Staff.findAll().list();
        if (staff.isEmpty()) {
            return Response.status(Response.Status.BAD_REQUEST).entity("PERAWAT_NOT_FOUND").build();
        }

        JsonObject result = new JsonObject();
        result.put("data", staff);
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
        return Response.ok().entity(result).build();
    }

    // Pagination Staff
    public Response getListDataStaff(Integer page){
        List<Staff> staff = new ArrayList<>();
        staff = Staff.findAll().page(page, 10).list();

        JsonObject result = new JsonObject();
        result.put("data", staff);
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
