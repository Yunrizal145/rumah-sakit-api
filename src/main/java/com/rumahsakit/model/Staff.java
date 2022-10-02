package com.rumahsakit.model;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import lombok.Getter;
import lombok.Setter;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "staff")
@Schema(name = "Rumah Sakit API", description = "Staff Table")
@Getter
@Setter
public class Staff extends PanacheEntityBase {

    @Id
    @SequenceGenerator(
            name = "idStaffSequence",
            sequenceName = "id_Staff_Sequence",
            initialValue = 1,
            allocationSize = 1)
    @GeneratedValue(generator = "idStaffSequence")
    @Column(name = "staffId", unique = true)
    private Long staffId;

    @Column(name = "nama_lengkap")
    private String namaLengkap;

    @Column(name = "gender")
    private String gender;

    @Column(name = "posisi")
    private String posisi;

    @Column(name = "start_time")
    private Date startTime;

    @Column(name = "end_time")
    private Date endTime;

    @Column(name = "gaji")
    private Long gaji;

    @Column(name = "email")
    private String email;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "status")
    private String status;

    @Column(name = "audit")
    private String audit;



}
