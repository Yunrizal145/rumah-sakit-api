package com.rumahsakit.model;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import lombok.Getter;
import lombok.Setter;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import javax.persistence.*;

@Entity
@Table(name = "obat")
@Schema(name = "Rumah Sakit API", description = "Obat Table")
@Getter
@Setter
public class Obat extends PanacheEntityBase {

    @Id
    @SequenceGenerator(
            name = "idObatSequence",
            sequenceName = "id_Obat_Sequence",
            initialValue = 1,
            allocationSize = 1)
    @GeneratedValue(generator = "idObatSequence")
    @Column(name = "obatId", unique = true)
    private Long obatId;

    @Column(name = "nama_obat")
    private String namaObat;

    @Column(name = "produksi")
    private String produksi;

    @Column(name = "obat_kategori")
    private String obatKategori;

    @Column(name = "deskripsi")
    private String deskripsi;

}
