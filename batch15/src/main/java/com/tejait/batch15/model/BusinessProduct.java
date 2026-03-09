package com.tejait.batch15.model;

import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="business_product")
public class BusinessProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int appId;
    private String purposeOfLoan;
    private String natureOfBusiness;
    private Integer tenure;
    private Long loanAmount;
}
