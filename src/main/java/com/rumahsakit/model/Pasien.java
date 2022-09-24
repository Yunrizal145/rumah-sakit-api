package com.rumahsakit.model;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import lombok.Getter;
import lombok.Setter;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import javax.persistence.*;

@Entity
@Table(name = "pasien")
@Schema(name = "Rumah Sakit API", description = "representation")
@Getter
@Setter
public class Pasien extends PanacheEntityBase {

    @Id
    @SequenceGenerator(
            name = "idPasienSequence",
            sequenceName = "id_Pasien_Sequence",
            initialValue = 1,
            allocationSize = 1)
    @GeneratedValue(generator = "idPasienSequence")
    @Column(name = "pasienId", nullable = false, unique = true)
    private Long pasienId;

    @Column(name = "nama_lengkap", nullable = false)
    private String namaLengkap;

    @Column(name = "gender", nullable = false)
    private String gender;

    @Column(name = "status", nullable = false)
    private String status;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "email")
    private String email;

    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;

    @Column(name = "is_cover_bpjs", nullable = false)
    private Boolean isCoverBpjs;

}
