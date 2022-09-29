package com.rumahsakit.model;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import lombok.Getter;
import lombok.Setter;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import javax.persistence.*;

@Entity
@Table(name = "user_permission")
@Schema(name = "Rumah Sakit API", description = "User Permision Table")
@Getter
@Setter
public class UserPermission extends PanacheEntityBase {

    @Id
    @SequenceGenerator(
            name = "idUserPermissionSequence",
            sequenceName = "id_User_Permission_Sequence",
            initialValue = 1,
            allocationSize = 1)
    @GeneratedValue(generator = "idUserPermissionSequence")
    @Column(name = "id", unique = true)
    private Long idUserPermission;

    // User Id
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "nama")
    private String nama;

}
