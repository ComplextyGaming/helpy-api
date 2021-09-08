package com.helpy.repository;

import com.helpy.model.Expert;
import org.springframework.stereotype.Repository;

@Repository
public interface ExpertRepository extends GenericRepository<Expert, Long> {
}
