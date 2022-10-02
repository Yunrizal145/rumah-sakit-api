package com.rumahsakit.model;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import lombok.Getter;
import lombok.Setter;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import javax.persistence.*;

@Entity
@Table(name = "resep_obat")
@Schema(name = "Rumah Sakit API", description = "Resep Obat Table")
@Getter
@Setter
public class ResepObat extends PanacheEntityBase {

    @Id
    @SequenceGenerator(
            name = "idResepObatSequence",
            sequenceName = "id_Resep_Obat_Sequence",
            initialValue = 1,
            allocationSize = 1)
    @GeneratedValue(generator = "idResepObatSequence")
    @Column(name = "resepObatId", unique = true)
    private Long resepObatId;

    // Pertemuan Id
    @Column(name = "pertemuan_id")
    private Long pertemuanId;

    // Obat Id
    @Column(name = "obat_id")
    private Long obatId;

    @Column(name = "dosis")
    private String dosis;

    @Column(name = "deskripsi")
    private String deskripsi;


}
