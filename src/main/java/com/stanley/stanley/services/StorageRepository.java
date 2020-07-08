package com.stanley.stanley.services;

import com.stanley.stanley.model.Author;
import com.stanley.stanley.model.Book;
import com.stanley.stanley.model.StoredBook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import javax.persistence.OptimisticLockException;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class StorageRepository {
    @PersistenceContext
    EntityManager em;
    @Autowired
    BookRepository bookRepository;

    @Transactional
    public void createStoredBook(String bookName, int qty) {
        Book book = bookRepository.findBookByName(bookName);
        if (book == null) {
            throw new IllegalArgumentException("no such book");
        }
        em.persist(new StoredBook(book, qty));
        em.flush();
    }

    @Transactional
    public void incrementDecrementStoredBook(int storedBookId, int qty) throws OptimisticLockException {
        StoredBook storedBook = em.find(StoredBook.class, storedBookId);
        if (storedBook == null) {
            throw new IllegalArgumentException("no such book in storage");
        }
        int newAmmount = storedBook.getQuantity() + qty;
        if (newAmmount < 0) {
            throw new IllegalArgumentException("not enough amount of books at storage");
        }
        em.lock(storedBook, LockModeType.OPTIMISTIC);
        storedBook.setQuantity(newAmmount);
        em.persist(storedBook);
    }

    @Transactional
    public List<StoredBook> allStoredBooks() {
        List<StoredBook> books = em.createQuery("SELECT b FROM StoredBook b", StoredBook.class).getResultList();
        if (books == null || books.size() == 0) {
            return null;
        }
        return books;
    }

    ;

    public List<StoredBook> search(String keyword) {
        List<StoredBook> books = em
                .createQuery("SELECT sb FROM StoredBook sb where sb.book.bookName LIKE CONCAT('%',:keyword,'%')", StoredBook.class)
                .setParameter("keyword", keyword).getResultList();
        if (books == null || books.size() == 0) {
            return null;
        }
        return books;
    }

}
