package com.rumahsakit.model;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import lombok.Getter;
import lombok.Setter;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "daftar_rawat_inap")
@Schema(name = "Rumah Sakit API", description = "Daftar Rawat Inap Table")
@Getter
@Setter
public class DaftarRawatInap extends PanacheEntityBase {

    @Id
    @SequenceGenerator(
            name = "idDaftarRawatInapSequence",
            sequenceName = "id_Daftar_Rawat_Inap_Sequence",
            initialValue = 1,
            allocationSize = 1)
    @GeneratedValue(generator = "idDaftarRawatInapSequence")
    @Column(name = "daftarRawatInapId", unique = true)
    private Long daftarRawatInapId;

//    // Pasien Id
//    @Column(name = "pasien_id")
//    private Pasien pasienId;
//
//    // Ruang Inap Id
//    @Column(name = "ruang_inap_id")
//    private RuangInap ruangInapId;
//
//    // Dokter Id
//    @Column(name = "dokter_id")
//    private Dokter dokterId;
//
//    // Perawat 1 Id
//    @Column(name = "perawat_satu_id")
//    private Perawat perawatSatuId;
//
//    // Perawat 2 Id
//    @Column(name = "perawat_dua_id")
//    private Perawat perawatDuaId;


    @Column(name = "start_datetime")
    private Timestamp startDateTime;

    @Column(name = "end_datetime")
    private Timestamp endDateTime;

    @Column(name = "is_checkout")
    private Boolean isCheckout;

}
