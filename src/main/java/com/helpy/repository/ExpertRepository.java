package com.helpy.repository;

import com.helpy.model.Expert;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExpertRepository extends GenericRepository<Expert, Long> {
    List<Expert> findByGameId(Long gameId);
}
