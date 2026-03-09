package com.tejait.batch15.serviceimpl;

import com.tejait.batch15.model.Employee;
import com.tejait.batch15.repository.EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceImplTests {
    @Mock     // create repository mockobject
    EmployeeRepository repository;
    @InjectMocks    // service bean injects into mock bean repository
    EmployeeServiceImpl service;
    @Test
    void givenEmp_whenSaveEmp_thenReturnSavedEmp() {
        Employee givenEmp = Employee.builder()
                .id(1)
                .fname("nageena")
                .lname("sk")
                //.fullname("nageena sk")
                .age(23)
                .dept("java")
                .salary(10000L)
                .empCode("java1234")
                .build();
        //when
        BDDMockito.given(repository.save(givenEmp)).willReturn(givenEmp);
        Employee savedEmp=service.saveEmployee(givenEmp);
        //then
        assertThat(savedEmp).isNotNull();
        assertThat(savedEmp.getId()).isPositive();
        assertThat(savedEmp.getAge()).isGreaterThan(18);
        assertThat(savedEmp.getDept()).isEqualTo("java");
        assertThat(savedEmp.getFullname()).isEqualTo("nageena sk");
        assertThat(savedEmp).hasFieldOrProperty("empCode");
        assertThat(savedEmp).hasFieldOrPropertyWithValue("fullname","nageena sk");
        assertThat(savedEmp).hasNoNullFieldsOrProperties();

    }
}
