package cinema.dao.impl;

import cinema.dao.UserDao;
import cinema.exceptions.DataProcessingException;
import cinema.lib.Dao;
import cinema.model.User;
import cinema.util.HibernateUtil;
import java.util.Optional;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

@Dao
public class UserDaoImpl implements UserDao {

    @Override
    public User add(User user) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.save(user);
            transaction.commit();
            return user;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Can't insert user Entity with id = "
                    + user.getId(), e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public Optional<User> findByEmail(String email) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<User> findByEmailQuery = session.createQuery("FROM User "
                    + "WHERE email = :email", User.class);
            findByEmailQuery.setParameter("email", email);
            return Optional.ofNullable(findByEmailQuery.uniqueResult());
        } catch (Exception e) {
            throw new RuntimeException("Can't find available account", e);
        }
    }
}
