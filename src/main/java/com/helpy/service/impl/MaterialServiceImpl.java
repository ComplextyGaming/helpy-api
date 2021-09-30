package com.helpy.service.impl;

import com.helpy.model.Material;
import com.helpy.repository.GenericRepository;
import com.helpy.repository.MaterialRepository;
import com.helpy.service.MaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MaterialServiceImpl extends CrudServiceImpl<Material, Long> implements MaterialService {

    @Autowired
    private MaterialRepository materialRepository;

    @Override
    protected GenericRepository<Material, Long> getRepository() {
        return materialRepository;
    }
}
