package com.example.book.respository;

import com.example.book.model.Bill;
import com.example.book.model.Book;
import com.example.book.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BillRepository extends JpaRepository<Bill,Long> {
    List<Bill> findByUser(User user);
    Bill findActiveBillByUser(User user);
    @Query("SELECT bd.bill FROM BillDetails bd WHERE bd.bill.user = :user AND bd.book = :book")
    Bill findActiveBillByUserAndBook(@Param("user") User user, @Param("book") Book book);

    List<Bill> findActiveBillsByUser(User user);
}
