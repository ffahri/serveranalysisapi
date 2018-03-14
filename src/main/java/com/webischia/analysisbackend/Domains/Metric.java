package com.webischia.analysisbackend.Domains;

import lombok.Data;

import javax.persistence.Entity;
import java.util.Date;

//@Entity
//@Data
public class Metric {
    private Date timestamp;
    private String instance;
    private Double value;

    //user -> izni var mı o metriğe - > metriği aç
    //admin -> izni var mı var -> aç
    //query/geliyor -> spring gidip prometheustan istiyor -> tekrar json olarak androide atıyor

}
