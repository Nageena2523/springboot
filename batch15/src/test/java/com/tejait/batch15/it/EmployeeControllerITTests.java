package com.tejait.batch15.it;

import com.tejait.batch15.model.Employee;
import com.tejait.batch15.service.EmployeeService;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import tools.jackson.databind.ObjectMapper;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
 class EmployeeControllerITTests {


    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper mapper;

    @Test
    void givenEmp_whenSaveEmp_thenReturnSavedEmp() throws Exception {
        Employee givenEmp = Employee.builder()
               // .id(1)
                .fname("nageena")
                .lname("sk")
                .fullname("nageena sk")
                .age(23)
                .dept("java")
                .salary(10000L)
                .empCode("java1234")
                .build();
       // BDDMockito.given(service.saveEmployee(givenEmp)).willReturn(givenEmp);

        ResultActions result = mockMvc.perform(post("http://localhost:8080/employee/saveEmp")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(givenEmp)));
        result.andExpect(MockMvcResultMatchers.status().isCreated());
        result.andExpect(MockMvcResultMatchers.jsonPath("$.fname").value("nageena"));
        result.andExpect(MockMvcResultMatchers.jsonPath("$.dept").value("java"));


    }
}
