package com.rumahsakit.model;

import lombok.Getter;
import lombok.Setter;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import javax.persistence.*;

@Entity
@Table(name = "perawat")
@Schema(name = "Rumah Sakit API", description = "representation")
@Getter
@Setter
public class Perawat {

    @Id
    @SequenceGenerator(
            name = "idPerawatSequence",
            sequenceName = "id_Perawat_Sequence",
            initialValue = 1,
            allocationSize = 1)
    @GeneratedValue(generator = "idPerawatSequence")
    @Column(name = "perawatId", nullable = false, unique = true)
    private Long perawatId;

    @Column(name = "nama_lengkap", nullable = false)
    private String namaLengkap;

    @Column(name = "gender", nullable = false)
    private String gender;

    @Column(name = "gaji", nullable = false)
    private Long gaji;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;

    @Column(name = "status", nullable = false)
    private String status;

}
