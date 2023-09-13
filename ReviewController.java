package com.miniProject.miniProjectTask.controller;

import com.miniProject.miniProjectTask.entity.Review;
import com.miniProject.miniProjectTask.model.ReviewDto;
import com.miniProject.miniProjectTask.service.ReviewServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class ReviewController {
    @Autowired
    private ReviewServiceImpl reviewService;

    @PostMapping("/{bookId}/reviews/create")
    public String addReview(@PathVariable Integer bookId, @RequestBody ReviewDto review) throws Exception {
        reviewService.addReview(review, bookId);
        return "Review has been uploaded";
    }

    @PutMapping("/{bookId}/reviews/{reviewId}/update")
    public String updateReview(@PathVariable Integer bookId,
                               @PathVariable Integer reviewId, @RequestBody ReviewDto review) throws Exception {
        reviewService.updateReview(bookId, reviewId, review);
        return "Review has been updated.";
    }

    @DeleteMapping("/{bookId}/reviews/{reviewId}/update")
    public String deleteReview(@PathVariable Integer bookId, @PathVariable Integer reviewId) throws Exception {
        reviewService.deleteReview(bookId, reviewId);
        return "Book has been deleted successfully.";
    }

    @GetMapping("/{bookId}")
    public ResponseEntity<List<Review>> getAllReview(@PathVariable Integer bookId) throws Exception {
        List<Review> reviews = reviewService.getReviewByBookId(bookId);
        return new ResponseEntity<>(reviews, HttpStatus.OK);
    }
}
