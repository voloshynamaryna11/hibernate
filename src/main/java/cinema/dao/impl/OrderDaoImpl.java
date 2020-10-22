package cinema.dao.impl;

import cinema.dao.OrderDao;
import cinema.exceptions.DataProcessingException;
import cinema.model.Order;
import cinema.model.User;
import java.util.List;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class OrderDaoImpl implements OrderDao {
    private static final Logger logger = Logger.getLogger(OrderDaoImpl.class);
    private final SessionFactory sessionFactory;

    public OrderDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Order add(Order order) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.save(order);
            transaction.commit();
            logger.info("Success! order object already in DB");
            return order;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Can't insert order Entity "
                    + order, e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public List<Order> getByUser(User user) {
        try (Session session = sessionFactory.openSession()) {
            Query<Order> orderQuery = session
                    .createQuery("select distinct u FROM Order u "
                                   + "LEFT JOIN FETCH u.tickets WHERE u.user.id = :userId ",
                            Order.class);
            orderQuery.setParameter("userId", user.getId());
            return orderQuery.getResultList();
        } catch (Exception e) {
            throw new RuntimeException("Can't find available orders associated with email = "
                    + user.getEmail(), e);
        }
    }
}
