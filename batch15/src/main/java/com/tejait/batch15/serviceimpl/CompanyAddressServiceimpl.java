package com.tejait.batch15.serviceimpl;

import com.tejait.batch15.model.CompanyAddress;
import com.tejait.batch15.repository.CompanyAddressRepository;
import com.tejait.batch15.service.CompanyAddressService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class CompanyAddressServiceimpl  implements CompanyAddressService {
    CompanyAddressRepository repository;
    @Override
    public CompanyAddress saveAddress(Integer appId, CompanyAddress address) {
        address.setAppId(appId);

        return repository.save(address);

    }

    @Override
    public Optional<CompanyAddress> getByAppId(Integer appId) {
        return repository.findByAppId(appId);
    }
}
