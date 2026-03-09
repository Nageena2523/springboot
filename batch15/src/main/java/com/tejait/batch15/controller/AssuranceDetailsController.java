package com.tejait.batch15.controller;

import com.tejait.batch15.model.AssuranceDetails;
import com.tejait.batch15.service.AssuranceDetailsService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import tools.jackson.databind.ObjectMapper;

import java.util.Arrays;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/loans")
public class AssuranceDetailsController {

    private AssuranceDetailsService service;
    private final ObjectMapper objectMapper = new ObjectMapper();




    @PostMapping("/saveJsonfileData/{appId}")
    public ResponseEntity<List<AssuranceDetails>> uploadJson(
            @PathVariable int appId,

            @RequestParam("file") MultipartFile file) {

        try {

            if (file.isEmpty()) {
                return ResponseEntity.badRequest().build();
            }


            AssuranceDetails[] arr =
                    objectMapper.readValue(file.getInputStream(), AssuranceDetails[].class);

            List<AssuranceDetails> list = Arrays.asList(arr);


            for (AssuranceDetails obj : list) {
                obj.setId(0);
            }

            List<AssuranceDetails> savedList = service.saveAll(list);

            return ResponseEntity.ok(savedList);

        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
    @GetMapping("/getPersonDetails/{appId}")
    public List<AssuranceDetails> getAll() {
        return service.getAll();
    }

}