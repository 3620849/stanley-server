package com.stanley.stanley;

import com.stanley.stanley.controllers.BookController;
import com.stanley.stanley.model.Author;
import com.stanley.stanley.model.Book;
import com.stanley.stanley.model.BookDTO;
import com.stanley.stanley.services.BookRepository;
import com.stanley.stanley.services.StorageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.EnableAsync;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
@EnableAsync
@SpringBootApplication
public class StanleyApplication {
	public static void main(String[] args) {
		SpringApplication.run(StanleyApplication.class, args);
	}
	@Autowired
	StorageRepository repository;
	@Autowired
	BookController bookController;
	@EventListener(ApplicationReadyEvent.class)
	private void addBooks() {
//		Author dima = new Author("Dima");
//		Author bibi = new Author("Benjamin");
//
//		Book how_to_work = new Book(dima, "How to work hard");
//		Book jta = new Book(dima, "Java for all");
//		Book htc = new Book(bibi, "How to create");
//		bookRepository.createNewBook(how_to_work);
//		bookRepository.createNewBook(jta);
//		bookRepository.createNewBook(htc);
		bookController.addBook(new BookDTO("how to study","dima",10));
		bookController.addBook(new BookDTO("Globus","Jonh Wick",5));
	}

}
