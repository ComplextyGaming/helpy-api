package com.helpy.service.impl;

import com.helpy.model.Expert;
import com.helpy.repository.ExpertRepository;
import com.helpy.repository.GenericRepository;
import com.helpy.service.ExpertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExpertServiceImpl extends  CrudServiceImpl<Expert, Long> implements ExpertService {
    @Autowired
    private ExpertRepository expertRepository;

    @Override
    protected GenericRepository<Expert, Long> getRepository() {
        return expertRepository;
    }
}
