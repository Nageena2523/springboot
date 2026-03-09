package com.tejait.batch15.service;

import com.tejait.batch15.model.SalesReport;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface SalesReportService {

    void uploadExcel(int appId, MultipartFile file);

    void saveAll(int appId, List<SalesReport> list);

    List<SalesReport> getAllByAppId(int appId);
}
