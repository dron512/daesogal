package com.shop.entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name="cert")
public class Cert extends BaseEntity{
    @Id
    @Column(name = "cert_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private int take_year;
    private int take_month;

    private String city;
    private String take_age;
    private String cert_gubun;

    private String cert_part;
    private String cert_name;
    private String take_number;

    private String gender;

}
