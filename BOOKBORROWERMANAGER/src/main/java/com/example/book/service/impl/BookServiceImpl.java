package com.example.book.service.impl;

import com.example.book.model.Book;
import com.example.book.respository.BookRepository;
import com.example.book.respository.UserRepository;
import com.example.book.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {
    @Autowired
    private BookRepository bookRepository;
    @Override
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }
    @Override
    public Book getBookById(Long bookId) {
        return bookRepository.findById(bookId).orElse(null);
    }
    @Override
    public Book addBook(Book book) {
        return bookRepository.save(book);
    }
    @Override
    public Book updateBook(Long bookId, Book updatedBook) {
        Book book = bookRepository.findById(bookId).orElse(null);
        if (book != null) {
            book.setBookName(updatedBook.getBookName());
            book.setAuthor(updatedBook.getAuthor());
            book.setInventory(updatedBook.getInventory());
            book.setState(updatedBook.getState());
            book.setPrice(updatedBook.getPrice());
            book.setDescription(updatedBook.getDescription());
            return bookRepository.save(book);
        }
        return null;
    }
    @Override
    public boolean deleteBook(Long bookId) {
        Optional<Book> book = bookRepository.findById(bookId);
        if (book.isPresent()) {
            bookRepository.delete(book.get());
            return true;
        }
        return false;
    }
    @Override
    public List<Book> getBooksByCategory(Long categoryId) {
        return bookRepository.findByCategoryId(categoryId);
    }
    @Override
    public List<Book> searchBooks(String keyword) {
        return bookRepository.findByBookNameContainingOrAuthorContaining(keyword, keyword);
    }
    @Override
    public List<Book> getBooksLowInventory(int threshold) {
        return bookRepository.findByInventoryLessThan(threshold);
    }
}
