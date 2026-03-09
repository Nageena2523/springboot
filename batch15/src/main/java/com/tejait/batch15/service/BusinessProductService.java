package com.tejait.batch15.service;

import com.tejait.batch15.model.BusinessProduct;

import java.util.List;
import java.util.Optional;

public interface BusinessProductService {
    BusinessProduct saveProduct( BusinessProduct products);

    List<BusinessProduct> getAll();

    Optional<BusinessProduct> getByAppId(Integer appId);


}
