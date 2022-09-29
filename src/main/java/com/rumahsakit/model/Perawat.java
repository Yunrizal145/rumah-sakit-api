package com.rumahsakit.model;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import lombok.Getter;
import lombok.Setter;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import javax.persistence.*;

@Entity
@Table(name = "perawat")
@Schema(name = "Rumah Sakit API", description = "Perawat Table")
@Getter
@Setter
public class Perawat extends PanacheEntityBase {

    @Id
    @SequenceGenerator(
            name = "idPerawatSequence",
            sequenceName = "Id_Perawat_Sequence",
            initialValue = 1,
            allocationSize = 1)
    @GeneratedValue(generator = "idPerawatSequence")
    @Column(name = "perawatId", unique = true)
    private Long perawatId;

    @Column(name = "nama_lengkap")
    private String namaLengkap;

    @Column(name = "gender")
    private String gender;

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

    @OneToMany
    @JoinColumn(name = "foreign_id")
    private DaftarShift daftarShift;

    @OneToMany
    @JoinColumn(name = "perawat_satu_id")
    private DaftarRawatInap daftarRawatInap1;

    @OneToMany
    @JoinColumn(name = "perawat_dua_id")
    private DaftarRawatInap daftarRawatInap2;
}
