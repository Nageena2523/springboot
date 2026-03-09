package com.tejait.batch15.service;

import com.tejait.batch15.model.AssuranceDetails;
import org.springframework.web.multipart.MultipartFile;


import java.util.List;

public interface AssuranceDetailsService {
    List<AssuranceDetails> saveAll(List<AssuranceDetails> detailsList);


    List<AssuranceDetails> getAll();
}
