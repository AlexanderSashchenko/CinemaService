package com.dev.cinema.dao.impl1hql;

import com.dev.cinema.dao.MovieSessionDao;
import com.dev.cinema.exceptions.DataProcessingException;
import com.dev.cinema.lib.Dao;
import com.dev.cinema.model.MovieSession;
import com.dev.cinema.util.HibernateUtil;

import java.time.LocalDate;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

@Dao
public class MovieSessionDaoImpl implements MovieSessionDao {
    @Override
    public MovieSession add(MovieSession movieSession) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Long itemId = (Long) session.save(movieSession);
            transaction.commit();
            movieSession.setId(itemId);
            return movieSession;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Failed to add movie session to database", e);
        }
    }

    @Override
    public List<MovieSession> findAvailableSessions(Long movieId, LocalDate date) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from MovieSession where movie.id = :movieId "
                    + "and year(showTime) = :year and month(showTime) = :month "
                    + "and day(showTime) = :day", MovieSession.class)
                    .setParameter("movieId", movieId)
                    .setParameter("year", date.getYear())
                    .setParameter("month", date.getMonthValue())
                    .setParameter("day", date.getDayOfMonth()).list();
        } catch (Exception e) {
            throw new DataProcessingException("Failed to find available movie sessions", e);
        }
    }
}
