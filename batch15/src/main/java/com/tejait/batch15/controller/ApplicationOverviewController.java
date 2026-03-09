package com.tejait.batch15.controller;

import com.tejait.batch15.dto.ApplicationOverview;
import com.tejait.batch15.service.ApplicationOverviewService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@AllArgsConstructor
@RestController
@RequestMapping("/loans")
public class ApplicationOverviewController {
    ApplicationOverviewService service;

    @GetMapping("/getOverviewDeatils/{appId}")
    public ResponseEntity<ApplicationOverview> getOverviewByAppId(@PathVariable int appId) {

        ApplicationOverview response = service.getOverviewByAppId(appId);
        return ResponseEntity.ok(response);
    }
}
