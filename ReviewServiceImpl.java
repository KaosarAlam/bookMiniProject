package com.miniProject.miniProjectTask.service;

import com.miniProject.miniProjectTask.entity.Book;
import com.miniProject.miniProjectTask.entity.Review;
import com.miniProject.miniProjectTask.model.ReviewDto;
import com.miniProject.miniProjectTask.repo.BookRepo;
import com.miniProject.miniProjectTask.repo.ReviewRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ReviewServiceImpl {

    @Autowired
    public ReviewRepo reviewRepository;
    @Autowired
    private BookRepo bookRepository;

    public Review addReview(ReviewDto reviewDto, Integer bookId) throws Exception{
        Optional<Book> bookEntityOptional = bookRepository.findById(bookId);

        if(bookEntityOptional.isEmpty()){
            throw new Exception("There is no book belong to this id.");
        }
        else {
            Review reviewEntity = new Review();
            reviewEntity.setReview(reviewDto.getReview());
            reviewEntity.setRatings(reviewEntity.getRatings());
            reviewEntity.setReviewTime(new Date());
            return reviewRepository.save(reviewEntity);
        }
    }

    public List<Review> getReviewByBookId(Integer bookId) throws Exception{
        List<Review> reviews = reviewRepository.getAllReviewByBookId(bookId);
        return reviews;
    }

    public void updateReview(Integer bookId, Integer reviewId, ReviewDto reviewDto) throws Exception{
        Review existingReview = reviewRepository.updateReviewByBookIdAndId(bookId, reviewId);


        existingReview.setReview(reviewDto.getReview());
        existingReview.setRatings(reviewDto.getRatings());
        existingReview.setReviewTime(new Date());


        reviewRepository.save(existingReview);
    }

    public void deleteReview(Integer bookId, Long reviewId) throws Exception{
        if (reviewRepository.findById(reviewId).isEmpty()){
            throw new Exception();
        }
        reviewRepository.deleteReviewByBookIdAndReviewId(reviewId, bookId);
    }
}

