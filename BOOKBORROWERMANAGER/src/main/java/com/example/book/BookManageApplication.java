package com.example.book;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;


@SpringBootApplication
@EntityScan("com.example.book.model")
public class BookManageApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookManageApplication.class, args);
	}

}
