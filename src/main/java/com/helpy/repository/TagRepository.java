package com.helpy.repository;
import com.helpy.model.Tag;
import org.springframework.stereotype.Repository;

@Repository
public interface TagRepository extends GenericRepository<Tag, Long> {
}
