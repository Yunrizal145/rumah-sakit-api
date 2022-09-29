package com.rumahsakit.model;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import lombok.Getter;
import lombok.Setter;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "daftar_pertemuan")
@Schema(name = "Rumah Sakit API", description = "Daftar Pertemuan Table")
@Getter
@Setter
public class DaftarPertemuan extends PanacheEntityBase {

    @Id
    @SequenceGenerator(
            name = "idDaftarPertemuanSequence",
            sequenceName = "id_Daftar_Pertemuan_Sequence",
            initialValue = 1,
            allocationSize = 1)
    @GeneratedValue(generator = "idDaftarPeretemuanSequence")
    @Column(name = "daftarPertemuanId", unique = true)
    private Long daftarPertemuanId;

    // Pasien Id
    @OneToMany
    @JoinColumn(name = "pasienId")
    private Pasien pasienId;

    // Dokter Id
    @OneToMany
    @JoinColumn(name = "dokterId")
    private Dokter dokterId;

    @Column(name = "kategori")
    private String kategori;

    @Column(name = "deskripsi")
    private String deskripsi;

    @Column(name = "tanggal")
    private Timestamp tanggal;

    @OneToMany
    @JoinColumn(name = "pertemuan_id")
    private ResepObat resepObat;
}
