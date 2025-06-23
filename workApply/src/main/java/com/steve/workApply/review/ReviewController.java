package com.steve.workApply.review;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/companies/{companyId}")
public class ReviewController {

    private ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping("/reviews")
    public ResponseEntity<List<Review>> getAllReviews(@PathVariable Long companyId){
        return new ResponseEntity<>(reviewService.getAllReviews(companyId),
                HttpStatus.OK);
    }

    @PostMapping("/reviews")
    public ResponseEntity<String> addReviews(@PathVariable Long companyId, @RequestBody Review review){
        boolean reviewAdd = reviewService.addReview(companyId, review);
        if(reviewAdd){
            return new ResponseEntity<>("Review Added Successfully", HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/reviews/{reviewId}")
    public ResponseEntity<Review> getReview(@PathVariable Long companyId,
                                                @PathVariable Long reviewId){
        Review review = reviewService.getReview(companyId,reviewId);
        if(review != null ){
            return new ResponseEntity<>(review, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/reviews/{reviewId}")
    public ResponseEntity<String> updateReview(@PathVariable Long companyId,
                                               @PathVariable Long reviewId,
                                               @RequestBody Review review){
        boolean isReviewUpdate = reviewService.updateReview(companyId, reviewId, review);
        if(isReviewUpdate)
            return new ResponseEntity<>("Review update Successfully", HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/reviews/{reviewId}")
    public ResponseEntity<String> deleteReview(@PathVariable Long companyId,
                                               @PathVariable Long reviewId){

        boolean isReviewUpdate = reviewService.deleteReview(companyId, reviewId);
        if(isReviewUpdate)
            return new ResponseEntity<>("Review deteled Successfully", HttpStatus.OK);
        else
            return new ResponseEntity<>("Review deteled NotFound", HttpStatus.NOT_FOUND);
    }
}
