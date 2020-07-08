package com.stanley.stanley.services;

import com.stanley.stanley.model.OrderBook;
import com.stanley.stanley.model.Status;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
@Repository
public class OrderRepository {
    @PersistenceContext
    private EntityManager em;
    @Transactional
    public void createOrder(OrderBook o){
        OrderBook orderBook = findOrder(o.getId());
        if(orderBook !=null){
            throw new IllegalArgumentException("order already exist");
        }
        em.persist(o);
    }
    @Transactional
    public void updateStatus( int orderId, Status status,String message){
        OrderBook o = findOrder(orderId);
        o.setStatus(status);
        o.setMessage(message);
        em.persist(o);
    }
    @Transactional
    public OrderBook findOrder(int id){
        return em.find(OrderBook.class,id);
    }
}
