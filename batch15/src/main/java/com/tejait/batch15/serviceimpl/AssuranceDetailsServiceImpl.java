package com.tejait.batch15.serviceimpl;

import tools.jackson.databind.ObjectMapper;
import com.tejait.batch15.model.AssuranceDetails;
import com.tejait.batch15.repository.AssuranceDetailsRepository;
import com.tejait.batch15.service.AssuranceDetailsService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;


@Service
@AllArgsConstructor
public class AssuranceDetailsServiceImpl implements AssuranceDetailsService {
    AssuranceDetailsRepository repository;


    @Override
    public List<AssuranceDetails> saveAll(List<AssuranceDetails> detailsList) {

        detailsList.forEach(data -> data.setId(0));
        return repository.saveAll(detailsList);
    }

    @Override
    public List<AssuranceDetails> getAll() {
        return repository.findAll();
    }
}
