package com.rumahsakit.model;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import lombok.Getter;
import lombok.Setter;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "staff")
@Schema(name = "Rumah Sakit API", description = "representation")
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
    @Column(name = "staffId", nullable = false, unique = true)
    private Long perawatId;

    @Column(name = "nama_lengkap", nullable = false)
    private String namaLengkap;

    @Column(name = "gender", nullable = false)
    private String gender;

    @Column(name = "posisi", nullable = false)
    private String posisi;

    @Column(name = "start_time", nullable = false)
    private Date startTime;

    @Column(name = "end_time", nullable = false)
    private Date endTime;

    @Column(name = "gaji", nullable = false)
    private Long gaji;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;

    @Column(name = "status", nullable = false)
    private String status;

}
