package com.tejait.batch15.controller;

import com.tejait.batch15.model.CompanyDetails;
import com.tejait.batch15.service.CompanyDetailsService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("loans")
public class CompanyDetailsController {
    CompanyDetailsService service;
    @PostMapping("saveCompanyDetails")
    public ResponseEntity<CompanyDetails>saveCompanyDetails(@RequestBody CompanyDetails companyDetails) {
        CompanyDetails savecompanyDetails = service.savecompanyDetails(companyDetails);
        return new ResponseEntity<>(savecompanyDetails, HttpStatus.CREATED);
    }
    @GetMapping("/getCompanyDetails/{appId}")
    public ResponseEntity<CompanyDetails> getByAppId(@PathVariable int appId) {

        CompanyDetails details = service.getByAppId(appId)
                .orElseThrow(() -> new RuntimeException("Company details not found with appId: " + appId));

        return ResponseEntity.ok(details);
    }



}
