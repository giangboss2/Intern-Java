package com.example.book.respository;

import com.example.book.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> findByCategoryId(Long categoryId);

    List<Book> findByBookNameContainingOrAuthorContaining(String bookName, String author);

    List<Book> findByInventoryLessThan(int threshold);
    Optional<Book> findById(Long id);
}

