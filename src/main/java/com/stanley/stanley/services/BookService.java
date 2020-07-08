package com.stanley.stanley.services;

import com.stanley.stanley.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BookService {
    @Autowired
    BookRepository bookRepository;
    @Autowired
    StorageRepository storageRepository;
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    BuyingExecutor buyingExecutor;
    public void addNewBook(BookDTO bookDTO){
        Author author = new Author(bookDTO.getAuthorName());
        bookRepository.createNewBook(new Book(author,bookDTO.getBookName()));
        storageRepository.createStoredBook(bookDTO.getBookName(),bookDTO.getQuantity());
    }
    public OrderBook buyBook(int storedBookId, int qty){
        OrderBook o = new OrderBook(Status.NEW);
        orderRepository.createOrder(o);
        buyingExecutor.starBuyingBook(o.getId(),storedBookId,qty);
        return o;
    }

}
