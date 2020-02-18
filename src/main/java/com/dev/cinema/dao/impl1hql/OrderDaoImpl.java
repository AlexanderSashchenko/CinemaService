package com.dev.cinema.dao.impl1hql;

import com.dev.cinema.dao.OrderDao;
import com.dev.cinema.exceptions.DataProcessingException;
import com.dev.cinema.model.Order;
import com.dev.cinema.model.User;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

@Repository
public class OrderDaoImpl implements OrderDao {
    private final SessionFactory sessionFactory;

    public OrderDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Order add(Order order) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            Long orderId = (Long) session.save(order);
            transaction.commit();
            order.setId(orderId);
            return order;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Failed to add order", e);
        }
    }

    @Override
    public List<Order> getUserOrders(User user) {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("from Order where user = :user", Order.class)
                    .setParameter("user", user).list();
        } catch (Exception e) {
            throw new DataProcessingException("Failed to get orders list by user", e);
        }
    }
}
