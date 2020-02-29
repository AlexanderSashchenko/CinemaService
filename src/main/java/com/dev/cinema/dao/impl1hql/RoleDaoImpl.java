package com.dev.cinema.dao.impl1hql;

import com.dev.cinema.dao.RoleDao;
import com.dev.cinema.exceptions.DataProcessingException;
import com.dev.cinema.model.Role;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

@Repository
public class RoleDaoImpl implements RoleDao {

    private final SessionFactory sessionFactory;

    public RoleDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Role add(Role role) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            Long roleId = (Long) session.save(role);
            transaction.commit();
            role.setId(roleId);
            return role;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Failed to add role", e);
        }
    }

    @Override
    public Role getByName(String roleName) {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("from Role where roleName = :roleName", Role.class)
            .setParameter("roleName", roleName).uniqueResult();
        } catch (Exception e) {
            throw new DataProcessingException("Failed to find role by name", e);
        }
    }
}
