package com.dev.cinema.dao.impl1hql;

import com.dev.cinema.dao.CinemaHallDao;
import com.dev.cinema.exceptions.DataProcessingException;
import com.dev.cinema.model.CinemaHall;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

@Repository
public class CinemaHallDaoImpl implements CinemaHallDao {
    private final SessionFactory sessionFactory;

    public CinemaHallDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public CinemaHall add(CinemaHall cinemaHall) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            Long itemId = (Long) session.save(cinemaHall);
            transaction.commit();
            cinemaHall.setId(itemId);
            return cinemaHall;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Failed to add cinema hall to database", e);
        }
    }

    @Override
    public List<CinemaHall> getAll() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("from CinemaHall", CinemaHall.class).list();
        } catch (Exception e) {
            throw new DataProcessingException("Failed to get all cinema halls from database", e);
        }
    }

    @Override
    public CinemaHall findById(Long id) {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("from CinemaHall all where id = :id", CinemaHall.class)
                    .setParameter("id", id).uniqueResult();
        } catch (Exception e) {
            throw new DataProcessingException("Filed to find cinema hall entity by id", e);
        }
    }
}
