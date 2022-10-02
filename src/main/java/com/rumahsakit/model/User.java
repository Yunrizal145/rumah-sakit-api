package com.rumahsakit.model;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import io.quarkus.security.jpa.Password;
import io.quarkus.security.jpa.Roles;
import io.quarkus.security.jpa.UserDefinition;
import io.quarkus.security.jpa.Username;
import lombok.Getter;
import lombok.Setter;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import javax.persistence.*;

@Entity
@Table(name = "user")
@Schema(name = "Rumah Sakit API", description = "User Table")
@Getter
@Setter
@UserDefinition
public class User extends PanacheEntityBase {

    @Id
    @SequenceGenerator(
            name = "idUserSequence",
            sequenceName = "id_User_Sequence",
            allocationSize = 1)
    @GeneratedValue(generator = "idUserSequence")
    @Column(name = "userId", unique = true)
    private Long userId;

    @Column(name = "name")
    private String name;

    @Column(name = "username")
    @Schema(required = true)
    @Username
    private String username;

    @Column(name = "password")
    @Schema(required = true)
    @Password
    private String password;

    @Column(name = "email")
    private String email;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "user_type")
    @Roles
    private String userType;


}
