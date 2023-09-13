package com.miniProject.miniProjectTask.repo;

import com.miniProject.miniProjectTask.entity.History;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface HistoryRepo extends JpaRepository<History, Long> {

  // "UPDATE BorrowedBookEntity bb SET bb.status = 'returned' WHERE bb.bookEntity = :bookId"
    void updateStatus(Integer bookId);
}
