package com.tejait.batch15.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="loan-b15")
public class Loan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int appId;
    private String fname;
    private String lname;
    private String customerName;
    private String city;
    private String mailId;
    private long mobile;

}
