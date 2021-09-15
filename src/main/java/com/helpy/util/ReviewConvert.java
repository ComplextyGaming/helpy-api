package com.helpy.util;

import com.helpy.dto.ReviewRequest;
import com.helpy.dto.ReviewResponse;
import com.helpy.model.Review;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ReviewConvert {

    @Autowired
    private ModelMapper modelMapper;

    public Review convertReviewToEntity(ReviewRequest request){
        return modelMapper.map(request, Review.class);
    }

    public ReviewResponse convertReviewToResponse(Review review){
        return modelMapper.map(review, ReviewResponse.class);
    }

    public List<ReviewResponse> convertReviewToResponse(List<Review> reviews){
        return reviews.stream().map(review -> modelMapper.map(review, ReviewResponse.class)).collect(Collectors.toList());
    }

}
