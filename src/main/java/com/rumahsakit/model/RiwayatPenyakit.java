package com.rumahsakit.model;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import lombok.Getter;
import lombok.Setter;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "daftar_riwayat_penyakit")
@Schema(name = "Rumah Sakit API", description = "Daftar Riwayat Penyakit Table")
@Getter
@Setter
public class RiwayatPenyakit extends PanacheEntityBase {

    @Id
    @SequenceGenerator(
            name = "idRiwayatPenyakitSequence",
            sequenceName = "id_Riwayat_Penyakit_Sequence",
            initialValue = 1,
            allocationSize = 1)
    @GeneratedValue(generator = "idRiwayatPenyakitSequence")
    @Column(name = "riwayatPenyakitId")
    private Long riwayatPenyakitId;



    @Column(name = "nama")
    private String nama;

    @Column(name = "deskripsi")
    private String deckripsi;

    @Column(name = "awal_date")
    private Date awalDate;

    @Column(name = "sembuh_date")
    private Date sembuhDate;
}
