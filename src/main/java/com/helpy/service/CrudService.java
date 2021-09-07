package com.helpy.service;
import com.helpy.repository.GenericRepository;

import java.util.List;
import java.util.Optional;

public interface CrudService<T, ID> {
    T create(T t) throws Exception;
    T update(T t) throws Exception;
    List<T> getAll() throws Exception;
    Optional<T> getById(ID id) throws  Exception;
    void delete(ID id) throws Exception;
}
