package com.example.review.controller;

import com.example.review.model.Review;
import com.example.review.repository.ReviewRepository;
import com.example.review.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    // Khởi tạo controller
    public ReviewController() {
    }

    // Trả về danh sách Review
    @GetMapping("/review")
    @ResponseBody
    public List<Review> getReviewList() {
        return reviewService.findAll();
    }


    @GetMapping("/review/{id}")
    public ResponseEntity<Review> getReviewById(@PathVariable("id") Long reviewId) {
        Review review = reviewService.findById(reviewId);
        if (review != null) {
            return ResponseEntity.ok(review);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/remove-review")
    @ResponseBody
    public ResponseEntity<?> removeReviewById(@RequestParam("id") Long reviewId) {
        try {
            reviewService.delete(reviewId);
            return ResponseEntity.ok(reviewService.findAll());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error deleting review: " + e.getMessage());
        }
    }

    @PostMapping("/create-review")
    public ResponseEntity<Review> createReview(@RequestBody Review review) {
        reviewService.save(review);
        return ResponseEntity.status(201).body(review);
    }

    @PutMapping("/update-review/{id}")
    public ResponseEntity<Review> updateReview(@PathVariable("id") Long reviewId, @RequestBody Review updateReview) {
        Review review = reviewService.findById(reviewId);
        if (review != null) {
            review.setReviewName(updateReview.getReviewName());
            review.setAuthorName(updateReview.getAuthorName());
            review.setProductId(updateReview.getProductId());
            review.setUserId(updateReview.getUserId());
            review.setRating(updateReview.getRating());
            review.setComment(updateReview.getComment());
            reviewService.save(review);
            return ResponseEntity.status(200).body(review);
        }
        return ResponseEntity.status(404).body(null);
    }

}
