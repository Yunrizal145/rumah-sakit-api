package com.rumahsakit.model;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import lombok.Getter;
import lombok.Setter;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import javax.persistence.*;

@Entity
@Table(name = "daftar_shift_hari")
@Schema(name = "Rumah Sakit API", description = "Daftar Shift Hari Table")
@Getter
@Setter
public class DaftarShiftHari extends PanacheEntityBase {

    @Id
    @SequenceGenerator(
            name = "idDaftarShiftHariSequence",
            sequenceName = "id_Daftar_Shift_Hari_Sequence",
            initialValue = 1,
            allocationSize = 1)
    @GeneratedValue(generator = "idDaftarShiftHariSequence")
    @Column(name = "daftarShiftHariId", unique = true)
    private Long daftarShiftHariId;

    // daftar shift id
    @Column(name = "daftar_shift_id")
    private Long daftarShiftId;

    @Column(name = "hari")
    private String hari;


}
