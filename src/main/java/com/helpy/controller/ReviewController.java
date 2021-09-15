package com.helpy.controller;

import com.helpy.dto.ReviewRequest;
import com.helpy.dto.ReviewResponse;
import com.helpy.exception.ResourceNotFoundException;
import com.helpy.service.ExpertService;
import com.helpy.service.PlayerService;
import com.helpy.service.ReviewService;
import com.helpy.util.ReviewConvert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/review")
public class ReviewController {

    @Autowired
    private ReviewConvert convert;

    @Autowired
    private ReviewService reviewService;

    @Autowired
    private PlayerService playerService;

    @Autowired
    private ExpertService expertService;

    @GetMapping
    public ResponseEntity<List<ReviewResponse>> getAll() throws Exception{
        var reviews = reviewService.getAll();
        return new ResponseEntity<>(convert.convertReviewToResponse(reviews), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReviewResponse> getById(@Valid @PathVariable(name = "id") Long id) throws Exception{
        var review = reviewService.getById(id).orElseThrow(() -> new ResourceNotFoundException("Review not found"));
        return new ResponseEntity<>(convert.convertReviewToResponse(review), HttpStatus.OK);
    }

    @PostMapping("/expert/{expertId}/player/{playerId}")
    public ResponseEntity<ReviewResponse> createReview(@Valid @RequestBody ReviewRequest request,
                                                       @Valid @PathVariable(name = "expertId") Long expertId,
                                                       @Valid @PathVariable(name = "playerId") Long playerId) throws Exception{
        var existExpert = expertService.getById(expertId).orElseThrow(() -> new ResourceNotFoundException("Expert not found"));
        var existPlayer = playerService.getById(playerId).orElseThrow(() -> new ResourceNotFoundException("Player not found"));

        var tem = convert.convertReviewToEntity(request);
        tem.setPlayer(existPlayer);
        tem.setExpert(existExpert);
        var review = reviewService.create(tem);
        return new ResponseEntity<>(convert.convertReviewToResponse(review), HttpStatus.ACCEPTED);
    }

    @PutMapping("/{id}/expert/{expertId}/player/{playerId}")
    public ResponseEntity<ReviewResponse> updateReview(@Valid @RequestBody ReviewRequest request,
                                                       @Valid @PathVariable(name = "id") Long id,
                                                       @Valid @PathVariable(name = "expertId") Long expertId,
                                                       @Valid @PathVariable(name = "playerId") Long playerId) throws Exception{
        var review = reviewService.getById(id).orElseThrow(() -> new ResourceNotFoundException("Review not exits"));
        var existExpert = expertService.getById(expertId).orElseThrow(() -> new ResourceNotFoundException("Expert not found"));
        var existPlayer = playerService.getById(playerId).orElseThrow(() -> new ResourceNotFoundException("Player not found"));

        review.setPlayer(existPlayer);
        review.setExpert(existExpert);
        review.setComment(request.getComment());
        review.setDescription(request.getDescription());
        review.setUrl(request.getUrl());
        reviewService.update(review);
        return new ResponseEntity<>(convert.convertReviewToResponse(review), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteReview(@Valid @PathVariable(name = "id") Long id) throws Exception{
        var review = reviewService.getById(id).orElseThrow(() -> new ResourceNotFoundException("Review not found"));
        reviewService.delete(id);
        return ResponseEntity.ok().build();
    }
}
