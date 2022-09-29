package com.rumahsakit.model;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import lombok.Getter;
import lombok.Setter;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import javax.persistence.*;

@Entity
@Table(name = "ruang_inap")
@Schema(name = "Rumah Sakit API", description = "Ruang Inap Table")
@Getter
@Setter
public class RuangInap extends PanacheEntityBase {

    @Id
    @SequenceGenerator(
            name = "idRuangInapSequence",
            sequenceName = "id_Ruang_Inap_Sequence",
            initialValue = 1,
            allocationSize = 1)
    @GeneratedValue(generator = "idRuangInapSequence")
    @Column(name = "ruang_inap_id")
    private Long ruangInapId;

    @Column(name = "prefix_ruangan")
    private String prefixRuangan;

    @Column(name = "nomor_ruangan")
    private String nomorRuangan;

    @Column(name = "kategori_ruangan")
    private String kategoriRuangan;

    @Column(name = "ïs_kosong")
    private Boolean isKosong;

    @OneToMany
    @JoinColumn(name = "ruang_inap_id")
    private DaftarRawatInap daftarRawatInap;
}
