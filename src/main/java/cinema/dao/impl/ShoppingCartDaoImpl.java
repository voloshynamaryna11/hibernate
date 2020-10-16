package cinema.dao.impl;

import cinema.dao.ShoppingCartDao;
import cinema.exceptions.DataProcessingException;
import cinema.lib.Dao;
import cinema.model.ShoppingCart;
import cinema.model.User;
import cinema.util.HibernateUtil;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

@Dao
public class ShoppingCartDaoImpl implements ShoppingCartDao {
    private static final Logger logger = Logger.getLogger(ShoppingCartDaoImpl.class);

    @Override
    public ShoppingCart add(ShoppingCart shoppingCart) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.save(shoppingCart);
            transaction.commit();
            logger.info("Success! shoppingCart object already in DB");
            return shoppingCart;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Can't insert shoppingCart Entity "
                    + shoppingCart, e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public ShoppingCart getByUser(User user) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<ShoppingCart> getByUserQuery = session.createQuery("select u FROM "
                            + "ShoppingCart u "
                            + "LEFT JOIN FETCH u.tickets f WHERE u.user.id = :user_id",
                    ShoppingCart.class);
            getByUserQuery.setParameter("user_id", user.getId());
            return getByUserQuery.uniqueResult();
        } catch (Exception e) {
            throw new DataProcessingException("Can't find available shoppingCart of user with id = "
                    + user.getId(), e);
        }
    }

    @Override
    public void update(ShoppingCart shoppingCart) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.saveOrUpdate(shoppingCart);
            transaction.commit();
            logger.info("Success! shoppingCart updated already in DB");
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Can't update shoppingCart Entity with id = "
                    + shoppingCart.getId(), e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}
