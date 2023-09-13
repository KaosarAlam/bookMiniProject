package com.miniProject.miniProjectTask.repo;

import com.miniProject.miniProjectTask.entity.Book;
import com.miniProject.miniProjectTask.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface BookRepo  extends JpaRepository<Book, Long>{
  //  "SELECT b " +
  //          "FROM BookEntity b " +
    //        "INNER JOIN b.borrowedBookEntity bb " +
      //      "WHERE bb.userEntity = :userId"
    List<Book> getBookBorrowedByUser(Integer userId);

    // "SELECT b " +
       //     "FROM BookEntity b " +
         //   "INNER JOIN b.borrowedBookEntity bb " +
           // "WHERE bb.userEntity = :userId AND bb.status = 'occupied'"
    List<Book> getOccupiedBooksBorrowedByUser(Integer userId);

    // "SELECT b " +
       //     "FROM BookEntity b " +
         //   "INNER JOIN b.borrowedBookEntity bb ON bb.bookEntity = :bookId " +
           // "WHERE bb.status = 'occupied'"
    Optional<Book> alreadyTakenBook(Integer bookId);
}
