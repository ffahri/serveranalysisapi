package com.webischia.analysisbackend.Domains;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private String surname;
    @Column(unique = true)
    private String email;
    private String phone;
    private String password;
    @Column(unique = true)
    private String username;

    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDate = new Date();

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "access_id", nullable = false)
    private AccessLevel accessLevel;

}
