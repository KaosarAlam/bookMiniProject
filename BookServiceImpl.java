package com.miniProject.miniProjectTask.service;

import com.miniProject.miniProjectTask.entity.Book;
import com.miniProject.miniProjectTask.model.BookDto;
import com.miniProject.miniProjectTask.repo.BookRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl {

    @Autowired
    private BookRepo bookRepository;

    public void addBook(BookDto book) throws Exception {
        ModelMapper modelMapper = new ModelMapper();
        Book bookEntity = new Book();
        if (book.getBookName() == null)
            throw new Exception();
        else {
            bookEntity.setBookName(book.getBookName());
            bookEntity.setAuthorName(book.getAuthorName());
            bookEntity.setPublishedYear(book.getPublishedYear());
            bookRepository.save(bookEntity);
        }
    }

    public List<Book> getAllBook() {
        return bookRepository.findAll();
    }

    public void deleteBook(Integer id) throws Exception {
        if (bookRepository.findById(id).isEmpty()) {
            throw new Exception();
        }
        bookRepository.deleteById(id);
    }

    public void updateBook(Integer id, BookDto book) throws Exception {
        Book existingBook = bookRepository.findById(id)
                .orElseThrow(Exception::new);


        existingBook.setBookName(book.getBookName());
        existingBook.setAuthorName(book.getAuthorName());
        existingBook.setPublishedYear(book.getPublishedYear());


        bookRepository.save(existingBook);
    }
}