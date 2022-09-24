package com.rumahsakit.model;

import lombok.Getter;
import lombok.Setter;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import javax.persistence.*;

@Entity
@Table(name = "dokter")
@Schema(name = "Rumah Sakit API", description = "representation")
@Getter
@Setter
public class Dokter {

    @Id
    @SequenceGenerator(
            name = "idDokterSequence",
            sequenceName = "id_Dokter_Sequence",
            initialValue = 1,
            allocationSize = 1)
    @GeneratedValue(generator = "idDokterSequence")
    @Column(name = "dokterId", nullable = false, unique = true)
    private Long dokterId;

    @Column(name = "nama_lengkap", nullable = false)
    private String namaLengkap;

    @Column(name = "is_spesialis", nullable = false)
    private Boolean isSpesialis;

    @Column(name = "spesialis_nama", nullable = false)
    private String spesialisNama;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;

    @Column(name = "status", nullable = false)
    private String status;

    @Column(name = "gaji", nullable = false)
    private Long gaji;
}
