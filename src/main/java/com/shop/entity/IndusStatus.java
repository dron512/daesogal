package com.shop.entity;

import lombok.Data;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
@ToString
public class IndusStatus extends BaseEntity {
    @Id
    @Column(name = "indus_id", nullable = false)
    private Long indus_id;
    private String part;
    private int year;
    private int eNumber;
    private int nNumber;

}
