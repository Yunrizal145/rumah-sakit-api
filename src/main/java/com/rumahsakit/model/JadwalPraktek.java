package com.rumahsakit.model;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import lombok.Getter;
import lombok.Setter;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "jadwal_praktek")
@Schema(name = "Rumah Sakit API", description = "Jadwal Praktek Table")
@Getter
@Setter
public class JadwalPraktek extends PanacheEntityBase {

    @Id
    @SequenceGenerator(
            name = "idJadwalPraktekSequence",
            sequenceName = "id_Jadwal_Praktek_Sequence",
            initialValue = 1,
            allocationSize = 1)
    @GeneratedValue(generator = "idJadwalPraktekSequence")
    @Column(name = "jadwalPraktekId")
    private Long jadwalPraktekId;

    @Column(name = "hari")
    private String hari;

    @Column(name = "start_time")
    private Timestamp startTime;

    @Column(name = "end_time")
    private Timestamp endTime;

    @Column(name = "deskripsi")
    private String deskripsi;

    // Dokter Id
    @ManyToOne
    @JoinColumn(name = "dokterId")
    private Dokter dokterId;

}
