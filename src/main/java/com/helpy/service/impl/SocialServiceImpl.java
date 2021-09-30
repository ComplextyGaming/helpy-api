package com.helpy.service.impl;

import com.helpy.model.Social;
import com.helpy.repository.GenericRepository;
import com.helpy.repository.SocialRepository;
import com.helpy.service.SocialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SocialServiceImpl extends CrudServiceImpl<Social, Long> implements SocialService {

    @Autowired
    private SocialRepository socialRepository;

    @Override
    protected GenericRepository<Social, Long> getRepository() {
        return socialRepository;
    }
}
