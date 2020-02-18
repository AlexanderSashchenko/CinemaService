package com.dev.cinema.dao.impl2cq;

import com.dev.cinema.dao.CinemaHallDao;
import com.dev.cinema.exceptions.DataProcessingException;
import com.dev.cinema.model.CinemaHall;

import java.util.List;

import javax.persistence.criteria.CriteriaQuery;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

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
            CriteriaQuery<CinemaHall> criteriaQuery = session.getCriteriaBuilder()
                    .createQuery(CinemaHall.class);
            criteriaQuery.from(CinemaHall.class);
            return session.createQuery(criteriaQuery).getResultList();
        } catch (Exception e) {
            throw new DataProcessingException("Failed to get all cinema halls from database", e);
        }
    }
}
