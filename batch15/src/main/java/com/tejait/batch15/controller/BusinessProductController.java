package com.tejait.batch15.controller;

import com.tejait.batch15.model.BusinessProduct;
import com.tejait.batch15.service.BusinessProductService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@RestController
@RequestMapping("loans")
public class BusinessProductController {
    BusinessProductService service;
    @PostMapping("/saveProductDetails")
    public ResponseEntity<BusinessProduct>saveProduct( @RequestBody BusinessProduct products){
        BusinessProduct  saveproducts= service.saveProduct(products);
        return new ResponseEntity<>(saveproducts , HttpStatus.CREATED);
    }
    @GetMapping("getProductDetails")
    public ResponseEntity<List<BusinessProduct>>getAll(){
        List<BusinessProduct> list= service.getAll();
        return new ResponseEntity<>(list,HttpStatus.OK);
    }
    @GetMapping("getProductDetails/{appId}")
    public ResponseEntity<BusinessProduct> getByAppId(@PathVariable Integer appId) {

        BusinessProduct product = service.getByAppId(appId)
                .orElseThrow(() -> new RuntimeException("Product not found with appId: " + appId));

        return ResponseEntity.ok(product);
    }

}

