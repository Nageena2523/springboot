package com.tejait.batch15.serviceimpl;

import com.tejait.batch15.model.BusinessProduct;
import com.tejait.batch15.repository.BusinessProductRepository;
import com.tejait.batch15.service.BusinessProductService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class BusinessProductServiceImpl implements BusinessProductService {
    BusinessProductRepository repository;


    @Override
    public BusinessProduct saveProduct(BusinessProduct products) {
        return repository.save(products);
    }

    @Override
    public List<BusinessProduct> getAll() {
        return repository.findAll();
    }

    @Override
    public Optional<BusinessProduct> getByAppId(Integer appId) {
        return repository.findByAppId(appId);
    }



}
