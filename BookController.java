package com.miniProject.miniProjectTask.controller;

import com.miniProject.miniProjectTask.entity.Book;
import com.miniProject.miniProjectTask.model.BookDto;
import com.miniProject.miniProjectTask.service.BookService;
import com.miniProject.miniProjectTask.service.BookServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {
    @Autowired
    private BookServiceImpl bookService;
    @PostMapping("/create")
    public String createNewBook(@RequestBody BookDto book) throws Exception {
        bookService.addBook(book);
        return "Book added to database";
    }

    @PutMapping("/update")
    public String updateBook(@RequestParam Integer bookId, @RequestBody BookDto book) throws Exception {
        bookService.updateBook(bookId, book);
        return "Data has been updated";
    }

    @DeleteMapping("/delete")
    public String deleteBook(@RequestParam Integer bookId) throws Exception {
        bookService.deleteBook(bookId);
        return "Book is removed from the database";
    }

    @GetMapping("/all")
    public ResponseEntity<List<Book>> getAllBookInfo(){
        return new ResponseEntity(bookService.getAllBook(), HttpStatus.OK );
    }
}
