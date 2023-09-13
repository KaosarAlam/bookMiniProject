package com.miniProject.miniProjectTask.repo;

import com.miniProject.miniProjectTask.entity.Review;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReviewRepo extends JpaRepository<Review, Long> {
    // "SELECT r FROM Review r WHERE r.book = :bookId"
    List<Review> getAllReviewByBookId(Integer bookId);

    // "SELECT r FROM Review r WHERE r.book = :bookId AND r.id = :reviewId"
    Review updateReviewByBookIdAndId(Integer bookId, Integer reviewId);



  //  "DELETE FROM Review r WHERE r.book = :bookId AND r.id = :reviewId"
    void deleteReviewByBookIdAndReviewId(Integer reviewId,Integer bookId);
}
