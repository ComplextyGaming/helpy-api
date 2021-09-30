package com.helpy.service.impl;

import com.helpy.model.Tag;
import com.helpy.repository.GenericRepository;
import com.helpy.repository.TagRepository;
import com.helpy.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TagServiceImpl extends CrudServiceImpl<Tag, Long> implements TagService {

    @Autowired
    private TagRepository tagRepository;

    @Override
    protected GenericRepository<Tag, Long> getRepository() {
        return tagRepository;
    }
}
