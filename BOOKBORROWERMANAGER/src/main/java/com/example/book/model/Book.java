package com.example.book.model;

import jakarta.persistence.*;
import jdk.jfr.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor

    @Table(name = "BOOK")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id")
    private Long bookId;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private com.example.book.model.Category category;

    @Column(name = "book_name")
    private String bookName;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private com.example.book.model.Author author;

    @Column(name = "inventory")
    private int inventory;

    @Column(name = "is_return")
    private boolean state=false;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "description")
    private String description;


    public boolean getState() {
        return true;
    }
}
