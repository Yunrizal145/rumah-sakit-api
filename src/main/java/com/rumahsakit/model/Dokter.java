package com.rumahsakit.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import lombok.Getter;
import lombok.Setter;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import javax.persistence.*;

@Entity
@Table(name = "dokter")
@Schema(name = "Rumah Sakit API", description = "Dokter Table")
@Getter
@Setter
public class Dokter extends PanacheEntityBase {

    @Id
    @SequenceGenerator(
            name = "idDokterSequence",
            sequenceName = "id_Dokter_Sequence",
            initialValue = 1,
            allocationSize = 1)
    @GeneratedValue(generator = "idDokterSequence")
    @Column(name = "dokterId", unique = true)
    private Long dokterId;

    @Column(name = "nama_lengkap")
    private String namaLengkap;

    @Column(name = "is_spesialis")
    private Boolean isSpesialis;

    @Column(name = "spesialis_nama")
    private String spesialisNama;

    @Column(name = "email")
    private String email;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "status")
    private String status;

    @Column(name = "gaji")
    private Long gaji;
}
