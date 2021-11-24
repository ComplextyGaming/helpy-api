package com.helpy.service.impl;

import com.helpy.dto.ExpertResponse;
import com.helpy.model.Expert;
import com.helpy.model.Rol;
import com.helpy.repository.ExpertRepository;
import com.helpy.repository.GenericRepository;
import com.helpy.service.ExpertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ExpertServiceImpl extends  CrudServiceImpl<Expert, Long> implements ExpertService {
    @Autowired
    private ExpertRepository expertRepository;
    @Autowired
    private BCryptPasswordEncoder bcrypt;
    @Override
    protected GenericRepository<Expert, Long> getRepository() {
        return expertRepository;
    }

    @Override
    public Expert create(Expert expert) throws Exception {
        expert.setPassword(bcrypt.encode(expert.getPassword()));
        var role = new Rol();
        role.setIdRol(1);
        expert.getRoles().add(role);
        return super.create(expert);
    }

    @Override
    public List<Expert> getAllByGameId(Long gameId) {
        return expertRepository.findByGameId(gameId);
    }
}
