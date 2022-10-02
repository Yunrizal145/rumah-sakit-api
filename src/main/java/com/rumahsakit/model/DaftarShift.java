package com.rumahsakit.model;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import lombok.Getter;
import lombok.Setter;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "daftar_shift")
@Schema(name = "Rumah Sakit API", description = "Daftar Shift Table")
@Getter
@Setter
public class DaftarShift extends PanacheEntityBase {

    @Id
    @SequenceGenerator(
            name = "idDaftarShiftSequence",
            sequenceName = "id_Daftar_Shift_Sequence",
            initialValue = 1,
            allocationSize = 1)
    @GeneratedValue(generator = "idDaftarShiftSequence")
    @Column(name = "daftarShiftId")
    private Long daftarShiftId;

    @Column(name = "kategori")
    private String kategori;

//     Foreign_id
    @Column(name = "foreign_id")
    private Long foreignId;


    @Column(name = "start_time")
    private String startTime;

    @Column(name = "end_time")
    private String endTime;

    @ManyToOne
    @JoinColumn(name = "staffId")
    private Staff staff;

    @ManyToOne
    @JoinColumn(name = "perawatId")
    private Perawat perawat;



}
