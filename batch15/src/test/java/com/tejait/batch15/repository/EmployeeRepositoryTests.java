package com.tejait.batch15.repository;

import com.tejait.batch15.model.Employee;

import org.assertj.core.api.Assertions.*;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.persistence.autoconfigure.EntityScan;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;


@DataJpaTest
@EntityScan(basePackageClasses = Employee.class)
public class EmployeeRepositoryTests {
    @Autowired
    EmployeeRepository repository;
    Employee givenEmp=null;
    Employee savedEmp= null;

    @Test
    void givenEmp_whenSaveEmp_thenReturnSavedEmp(){
      givenEmp  =Employee.builder()
             // .id(1)
              .fname("nageena")
              .lname("sk")
              .fullname("nageena sk")
              .age(23)
              .dept("java")
              .salary(10000L)
              .empCode("java1234")
              .build();
      savedEmp=repository.save(givenEmp);
        assertThat(savedEmp).isNotNull();
        assertThat(savedEmp.getId()).isPositive();
        assertThat(savedEmp.getAge()).isGreaterThan(18);
        assertThat(savedEmp.getDept()).isEqualTo("java");
        assertThat(savedEmp.getFullname()).isEqualTo("nageena sk");
        assertThat(savedEmp).hasFieldOrProperty("empCode");
        assertThat(savedEmp).hasFieldOrPropertyWithValue("fullname","nageena sk");
        assertThat(savedEmp).hasNoNullFieldsOrProperties();

    }
    @Test
    void givenId_whenFindBYId_getIdObject(){
        givenEmp  =Employee.builder()
                // .id(1)
                .fname("nageena")
                .lname("sk")
                .fullname("nageena sk")
                .age(23)
                .dept("java")
                .salary(10000L)
                .empCode("java1234")
                .build();
        Employee savedEmp= repository.save(givenEmp);
      Optional<Employee> employee =repository.findById(savedEmp.getId());
       assertThat(employee.get()).isNotNull();
       assertThat(employee.get().getEmpCode()).isEqualTo("java1234");

    }
    @Test
    void givenAll_whenFindAllEmp_getAllObject(){
        givenEmp  =Employee.builder()
                // .id(1)
                .fname("nageena")
                .lname("sk")
                .fullname("nageena sk")
                .age(23)
                .dept("java")
                .salary(10000L)
                .empCode("java1234")
                .build();
        Employee savedEmp= repository.save(givenEmp);
        List<Employee> employee=repository.findAll();
        assertThat(employee).isNotNull();
        assertThat(employee.size()).isGreaterThan(0);


    }

}
