package com.example.review.service;
import com.example.review.model.Review;
import com.example.review.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ReviewService {
    @Autowired
    private ReviewRepository reviewRepository;

    public List<Review> findAll() {
        return reviewRepository.findAll();
    }

    public Review findById(Long id) {
        return reviewRepository.findById(id).orElseThrow(() -> new RuntimeException("Review not found"));
    }

    public Review save(Review Review) {
        return reviewRepository.save(Review);
    }

    public Review update(Review Review) {
        return reviewRepository.save(Review);
    }

    public void delete(Long id) {
        reviewRepository.deleteById(id);
    }
}
