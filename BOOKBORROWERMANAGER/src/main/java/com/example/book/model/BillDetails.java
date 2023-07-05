package com.example.book.model;


import jakarta.persistence.*;
import lombok.Data;
import org.springframework.stereotype.Service;

@Service
@Data
@Entity
@Table(name = "BILLDETAILS")
public class
BillDetails {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long BilldetailId ;

    @ManyToOne
    @JoinColumn(name = "bill_id")
    private Bill bill;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;

    @Column(name = "quantity")
    private Integer quantity;

}
