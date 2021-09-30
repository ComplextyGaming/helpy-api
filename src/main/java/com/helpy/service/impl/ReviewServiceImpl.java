package com.helpy.service.impl;

import com.helpy.model.Review;
import com.helpy.repository.GenericRepository;
import com.helpy.repository.ReviewRepository;
import com.helpy.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReviewServiceImpl extends CrudServiceImpl<Review, Long>  implements ReviewService{

    @Autowired
    private ReviewRepository reviewRepository;

    @Override
    protected GenericRepository<Review, Long> getRepository() {
        return reviewRepository;
    }
}
