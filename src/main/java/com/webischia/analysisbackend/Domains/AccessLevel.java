package com.webischia.analysisbackend.Domains;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
public class AccessLevel {

    //gerek var mı tartışılması için.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String description;

    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDate = new Date();


}
