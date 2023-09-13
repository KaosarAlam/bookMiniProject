package com.miniProject.miniProjectTask.controller;

import com.miniProject.miniProjectTask.entity.Book;
import com.miniProject.miniProjectTask.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Optional;

@RestController
@RequestMapping("/books")
public class HistoryController {
    @Autowired
    public UserServiceImpl userService;
    @GetMapping("/{bookId}/borrow")
    public String borrowingBook(@PathVariable Integer bookId, @RequestParam Date date) throws Exception {
        Optional<Book> borrowedBook = userService.borrowedBookById(bookId, date);
        return "Please return the book within " + date;
    }

    @PutMapping("/{bookId}/return")
    public String returnBook(@PathVariable Integer bookId){
        userService.returnBook(bookId);
        return "Book has been returned by user";
    }
}
