package com.example.book.service;
import com.example.book.DTO.UserWithRoleDTO;
import com.example.book.model.Book;
import com.example.book.model.User;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;


@Service
public interface UserService {
    User findByUserName(String userName);
    User saveUser(User user);
    User addUser(User user);
    void deleteUser(Long userId);
    User updateUser(User user);
    ResponseEntity<String> loginUser(String username, String password);
    List<User> getAllUser();
    /*List<UserWithRoleDTO> getAllUsersWithRole();*/
    boolean existsByEmail(String email);
    /*void borrowBook(Long userId, Long bookId);*/
    User findById(Long userId);
    List<Book> getBorrowedBooks(Long userId);
    void borrowBook(Long userId, Long bookId,Integer quantity);
   /* ReturnBookResponse returnBook(Long userId);*/
    BigDecimal calculateLatePaymentFee(int daysLate);



}






