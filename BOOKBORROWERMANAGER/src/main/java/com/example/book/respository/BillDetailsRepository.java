package com.example.book.respository;

import com.example.book.model.Bill;
import com.example.book.model.BillDetails;
import com.example.book.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BillDetailsRepository extends JpaRepository<BillDetails, Long> {
    List<BillDetails> findByBill(Bill bill);
    void deleteByBillAndBook(Bill bill, Book book);

}

