package com.tejait.batch15.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name="company_address")
public class CompanyAddress {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int appId;
    private String flatnum;
    private String building;
    private String line;
    private String state;
    private String city;
    private Long pincode;
    private String landmark;
    private String area;
}
