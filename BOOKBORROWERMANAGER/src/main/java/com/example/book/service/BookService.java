package com.example.book.service;

import com.example.book.model.Book;
import com.example.book.respository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface BookService {

     List<Book> getAllBooks();
     Book getBookById(Long bookId);

     Book addBook(Book book);

     Book updateBook(Long bookId, Book updatedBook);

     boolean deleteBook(Long bookId);

     List<Book> getBooksByCategory(Long categoryId);

     List<Book> searchBooks(String keyword);
      List<Book> getBooksLowInventory(int threshold);
}




