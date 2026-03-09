package com.tejait.batch15.service;

import com.tejait.batch15.model.CompanyDetails;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface CompanyDetailsService  {
    CompanyDetails savecompanyDetails(CompanyDetails companyDetails);

    Optional<CompanyDetails> getByAppId(int appId);
}
