package com.rumahsakit.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
            sequenceName = "id_Perawat_Sequence",
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

}
