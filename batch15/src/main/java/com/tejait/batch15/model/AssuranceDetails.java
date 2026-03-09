package com.tejait.batch15.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name="AssuranceDetails")
public class AssuranceDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String ename;
    private String nationality;
    private int age;
    private String mail;
    private String gender;
}
