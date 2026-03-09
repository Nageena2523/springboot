package com.tejait.batch15.serviceimpl;

import com.tejait.batch15.model.CompanyDetails;
import com.tejait.batch15.repository.CompanyDetailsRepository;
import com.tejait.batch15.service.CompanyDetailsService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class CompanyDetailsServiceImpl implements CompanyDetailsService {

    CompanyDetailsRepository repository;
    @Override
    public CompanyDetails savecompanyDetails(CompanyDetails companyDetails) {
        return repository.save(companyDetails);
    }

    @Override
    public Optional<CompanyDetails> getByAppId(int appId) {
        return repository.findByAppId(appId);
    }
}
