package com.helpy.service.impl;

import com.helpy.repository.GenericRepository;
import com.helpy.service.CrudService;

import java.util.List;
import java.util.Optional;

public abstract class CrudServiceImpl<T, ID> implements CrudService<T, ID> {

    protected abstract GenericRepository<T, ID> getRepository();

    @Override
    public T create(T t) throws Exception {
        return getRepository().save(t);
    }

    @Override
    public T update(T t) throws Exception {
        return getRepository().save(t);
    }

    @Override
    public List<T> getAll() throws Exception {
        return getRepository().findAll();
    }

    @Override
    public Optional<T> getById(ID id) throws Exception {
        return getRepository().findById(id);
    }

    @Override
    public void delete(ID id) throws Exception {
        getRepository().deleteById(id);
    }
}
