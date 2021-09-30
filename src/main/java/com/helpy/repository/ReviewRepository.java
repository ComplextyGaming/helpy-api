package com.helpy.repository;

import com.helpy.model.Review;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends GenericRepository<Review, Long> {
}