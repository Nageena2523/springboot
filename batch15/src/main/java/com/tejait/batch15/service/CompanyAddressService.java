package com.tejait.batch15.service;

import com.tejait.batch15.model.CompanyAddress;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface CompanyAddressService {
    CompanyAddress saveAddress(Integer appId, CompanyAddress address);

    Optional<CompanyAddress> getByAppId(Integer appId);
}
