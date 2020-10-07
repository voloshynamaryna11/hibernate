package cinema.dao.impl;

import cinema.dao.MovieSessionDao;
import cinema.exceptions.DataProcessingException;
import cinema.lib.Dao;
import cinema.model.MovieSession;
import cinema.util.HibernateUtil;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

@Dao
public class MovieSessionDaoImpl implements MovieSessionDao {

    @Override
    public List<MovieSession> findAvailableSessions(Long movieId, LocalDate date) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<MovieSession> findAvailableSessionsQuery = session
                    .createQuery("FROM MovieSession WHERE movie.id = :movieId "
                            + "AND showTime > :start AND showTime < :end",
                            MovieSession.class);
            findAvailableSessionsQuery.setParameter("movieId", movieId);
            findAvailableSessionsQuery.setParameter("start", date.atTime(LocalTime.MIN));
            findAvailableSessionsQuery.setParameter("end", date.atTime(LocalTime.MAX));
            return findAvailableSessionsQuery.getResultList();
        } catch (Exception e) {
            throw new RuntimeException("Can't find available sessions", e);
        }
    }

    @Override
    public MovieSession add(MovieSession movieSession) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.save(movieSession);
            transaction.commit();
            return movieSession;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Can't insert movie session Entity", e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}
