package cinema.dao.impl;

import cinema.dao.MovieDao;
import cinema.exceptions.DataProcessingException;
import cinema.model.Movie;
import java.util.List;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class MovieDaoImpl implements MovieDao {
    private static final Logger logger = Logger.getLogger(MovieDaoImpl.class);
    private final SessionFactory sessionFactory;

    public MovieDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Movie add(Movie movie) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.save(movie);
            transaction.commit();
            logger.info("Success! movie object already in DB");
            return movie;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Can't insert movie Entity with title = "
                    + movie.getTitle(), e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    public List<Movie> getAll() {
        try (Session session = sessionFactory.openSession()) {
            Query<Movie> getAllMoviesQuery = session.createQuery("from Movie", Movie.class);
            return getAllMoviesQuery.getResultList();
        } catch (Exception e) {
            throw new DataProcessingException("Can't get all movies", e);
        }
    }

    @Override
    public Movie get(Long id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Movie.class, id);
        } catch (Exception e) {
            throw new DataProcessingException("Can't get movie with id = " + id, e);
        }
    }
}
