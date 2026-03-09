package com.tejait.batch15.controller;

import com.tejait.batch15.model.CompanyAddress;
import com.tejait.batch15.service.CompanyAddressService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("loans")
public class CompanyAddressController {
    CompanyAddressService service;
    @PostMapping("/saveCompanyAddress/{appId}")
    public ResponseEntity<CompanyAddress> saveAddress(
            @PathVariable Integer appId,
            @RequestBody CompanyAddress address) {

        CompanyAddress saved = service.saveAddress(appId, address);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }
    @GetMapping("/getCompanyAddress/{appId}")
    public ResponseEntity<CompanyAddress> getByAppId(@PathVariable Integer appId) {

        CompanyAddress address = service.getByAppId(appId)
                .orElseThrow(() -> new RuntimeException("Address not found with appId: " + appId));

        return ResponseEntity.ok(address);
    }
}
