package com.tejait.batch15.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
@Data
@Table(name="employee_b15")
@Entity
public class Employee {
	@Id                //primary key
	@GeneratedValue(strategy=GenerationType.IDENTITY)   // auto increment
	private int id;
	//@Column(name="first_name")
	private String fname;
	private String lname;
	private String fullname;
	private String dept;
	private Integer age;
	private long salary;
	private String empCode;
	
	
}
